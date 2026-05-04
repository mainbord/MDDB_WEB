package com.mddb.usecase.impl;

import com.mddb.client.DeviceClient;
import com.mddb.dao.DeviceRepository;
import com.mddb.dao.DeviceRepositorySetter;
import com.mddb.domain.Device;
import com.mddb.usecase.DeviceLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoaderServiceImpl implements DeviceLoader {

    private static final int PARALLELISM = 4;

    private final DeviceRepository repository;

    @Qualifier("phoneDbClient")
    private final DeviceClient phoneDbClient;

    @Qualifier("deviceSpecificationsClient")
    private final DeviceClient deviceSpecificationsClient;

    private final DeviceRepositorySetter deviceRepositorySetter;

    @Override
    @Async
    public void loadDevices(String source) {
        DeviceClient deviceClient = getDeviceClient(source);
        log.info("loadDevices started for source={}", source);

        try {
            if (deviceClient.supportsParallelPageLoading()) {
                loadDevicesInParallel(deviceClient);
            } else {
                loadDevicesSequentially(deviceClient);
            }
        } finally {
            deviceClient.resetPageNumber();
        }

        log.info("ALL devices saved");
    }

    private void loadDevicesSequentially(DeviceClient deviceClient) {
        while (true) {
            List<Device> devices = deviceClient.getDevicesWithPaging();
            if (devices == null || devices.isEmpty()) {
                break;
            }
            saveOnlyNewDevices(devices);
        }
    }

    private void loadDevicesInParallel(DeviceClient deviceClient) {
        ExecutorService executor = Executors.newFixedThreadPool(PARALLELISM);
        AtomicInteger nextPage = new AtomicInteger(0);
        AtomicBoolean stopRequested = new AtomicBoolean(false);

        List<Callable<Void>> workers = IntStream.range(0, PARALLELISM)
                .<Callable<Void>>mapToObj(index -> () -> {
                    while (!stopRequested.get()) {
                        int page = nextPage.getAndAdd(deviceClient.pageStep());
                        List<Device> devices = deviceClient.getDevicesByPage(page);

                        if (devices == null || devices.isEmpty()) {
                            stopRequested.set(true);
                            return null;
                        }

                        saveOnlyNewDevices(devices);
                    }
                    return null;
                })
                .toList();

        try {
            List<Future<Void>> futures = executor.invokeAll(workers);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Device loading interrupted", e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new RuntimeException(cause);
        } finally {
            executor.shutdownNow();
        }
    }

    private void saveOnlyNewDevices(List<Device> devices) {
        List<Integer> ids = devices.stream()
                .map(Device::getIdPhonedb)
                .filter(Objects::nonNull)
                .toList();

        if (ids.isEmpty()) {
            return;
        }

        Set<Integer> existingIds = repository.findByIdPhonedbIn(ids).stream()
                .map(Device::getIdPhonedb)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<Device> newDevices = devices.stream()
                .filter(device -> device.getIdPhonedb() != null)
                .filter(device -> !existingIds.contains(device.getIdPhonedb()))
                .toList();

        if (!newDevices.isEmpty()) {
            deviceRepositorySetter.saveAll(newDevices);
            log.info("saved devices count {}", newDevices.size());
        }
    }

    private DeviceClient getDeviceClient(String clientName) {
        return switch (clientName) {
            case "phoneDb" -> phoneDbClient;
            case "deviceSpecifications" -> deviceSpecificationsClient;
            default -> throw new RuntimeException("Not found " + clientName);
        };
    }
}
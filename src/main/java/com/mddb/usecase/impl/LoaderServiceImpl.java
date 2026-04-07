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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoaderServiceImpl implements DeviceLoader {

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
            while (true) {
                List<Device> devices = deviceClient.getDevicesWithPaging();
                if (devices == null || devices.isEmpty()) {
                    break;
                }

                List<Integer> ids = devices.stream()
                        .map(Device::getIdPhonedb)
                        .filter(Objects::nonNull)
                        .toList();

                if (ids.isEmpty()) {
                    log.info("skip page without idPhonedb");
                    continue;
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
        } finally {
            deviceClient.resetPageNumber();
        }

        log.info("ALL devices saved");
    }

    private DeviceClient getDeviceClient(String clientName) {
        return switch (clientName) {
            case "phoneDb" -> phoneDbClient;
            case "deviceSpecifications" -> deviceSpecificationsClient;
            default -> throw new RuntimeException("Not found " + clientName);
        };
    }
}
package com.mddb.usecase.impl;

import com.mddb.client.DeviceClient;
import com.mddb.dao.DeviceRepository;
import com.mddb.dao.DeviceRepositorySetter;
import com.mddb.domain.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoaderServiceImplTest {

    @Mock
    private DeviceRepository repository;

    @Mock
    private DeviceClient phoneDbClient;

    @Mock
    private DeviceClient deviceSpecificationsClient;

    @Mock
    private DeviceRepositorySetter deviceRepositorySetter;

    private LoaderServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new LoaderServiceImpl(
                repository,
                phoneDbClient,
                deviceSpecificationsClient,
                deviceRepositorySetter
        );
    }

    @Test
    void loadDevices_shouldUsePhoneDbClient_andSaveAllDevices_whenNoExistingFound() {
        Device first = device(101);
        Device second = device(102);
        List<Device> page = List.of(first, second);

        when(phoneDbClient.getDevicesWithPaging())
                .thenReturn(page)
                .thenReturn(List.of());

        when(repository.findByIdPhonedbIn(eq(List.of(101, 102))))
                .thenReturn(List.of());

        service.loadDevices("phoneDb");

        verify(phoneDbClient, times(2)).getDevicesWithPaging();
        verify(phoneDbClient).resetPageNumber();
        verifyNoInteractions(deviceSpecificationsClient);

        verify(repository).findByIdPhonedbIn(eq(List.of(101, 102)));
        verify(deviceRepositorySetter).saveAll(page);
    }

    @Test
    void loadDevices_shouldUseDeviceSpecificationsClient() {
        Device device = device(201);
        List<Device> page = List.of(device);

        when(deviceSpecificationsClient.getDevicesWithPaging())
                .thenReturn(page)
                .thenReturn(List.of());

        when(repository.findByIdPhonedbIn(eq(List.of(201))))
                .thenReturn(List.of());

        service.loadDevices("deviceSpecifications");

        verify(deviceSpecificationsClient, times(2)).getDevicesWithPaging();
        verify(deviceSpecificationsClient).resetPageNumber();
        verifyNoInteractions(phoneDbClient);

        verify(repository).findByIdPhonedbIn(eq(List.of(201)));
        verify(deviceRepositorySetter).saveAll(page);
    }

    @Test
    void loadDevices_shouldSaveOnlyNewDevices_whenSomeDevicesAlreadyExist() {
        Device existing = device(301);
        Device fresh = device(302);

        when(phoneDbClient.getDevicesWithPaging())
                .thenReturn(List.of(existing, fresh))
                .thenReturn(List.of());

        when(repository.findByIdPhonedbIn(eq(List.of(301, 302))))
                .thenReturn(List.of(existing));

        service.loadDevices("phoneDb");

        verify(phoneDbClient, times(2)).getDevicesWithPaging();
        verify(repository).findByIdPhonedbIn(eq(List.of(301, 302)));
        verify(deviceRepositorySetter).saveAll(List.of(fresh));
        verify(phoneDbClient).resetPageNumber();
    }

    @Test
    void loadDevices_shouldStop_whenFirstPageIsEmpty() {
        when(phoneDbClient.getDevicesWithPaging()).thenReturn(List.of());

        service.loadDevices("phoneDb");

        verify(phoneDbClient, times(1)).getDevicesWithPaging();
        verify(phoneDbClient).resetPageNumber();
        verifyNoInteractions(repository, deviceRepositorySetter);
    }

    @Test
    void loadDevices_shouldRequestDifferentPages_whenClientSupportsParallelPageLoading() {
        ParallelDeviceClient parallelPhoneDbClient = new ParallelDeviceClient();
        service = new LoaderServiceImpl(
                repository,
                parallelPhoneDbClient,
                deviceSpecificationsClient,
                deviceRepositorySetter
        );

        when(repository.findByIdPhonedbIn(anyList()))
                .thenReturn(List.of());

        service.loadDevices("phoneDb");

        List<Integer> requestedPages = new ArrayList<>(parallelPhoneDbClient.requestedPages);
        assertTrue(requestedPages.containsAll(List.of(0, 29, 58, 87)));
        assertEquals(4, requestedPages.stream()
                .filter(page -> List.of(0, 29, 58, 87).contains(page))
                .count());
    }

    @Test
    void loadDevices_shouldResetPageNumber_whenRepositoryThrowsException() {
        Device device = device(401);

        when(phoneDbClient.getDevicesWithPaging())
                .thenReturn(List.of(device));

        when(repository.findByIdPhonedbIn(anyList()))
                .thenThrow(new IllegalStateException("db error"));

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.loadDevices("phoneDb")
        );

        assertEquals("db error", exception.getMessage());
        verify(phoneDbClient, times(1)).getDevicesWithPaging();
        verify(phoneDbClient).resetPageNumber();
        verify(deviceRepositorySetter, never()).saveAll(any());
    }

    @Test
    void loadDevices_shouldThrowException_whenSourceIsUnknown() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.loadDevices("unknown")
        );

        assertEquals("Not found unknown", exception.getMessage());
        verifyNoInteractions(phoneDbClient, deviceSpecificationsClient, repository, deviceRepositorySetter);
    }

    private static Device device(Integer idPhonedb) {
        return new Device(
                null,
                idPhonedb,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private static class ParallelDeviceClient implements DeviceClient {

        private final AtomicInteger callCount = new AtomicInteger();
        private final CountDownLatch firstBatchStarted = new CountDownLatch(4);
        private final ConcurrentLinkedQueue<Integer> requestedPages = new ConcurrentLinkedQueue<>();

        @Override
        public List<Device> getDevicesWithPaging() {
            throw new UnsupportedOperationException("Parallel loader must request a concrete page");
        }

        @Override
        public List<Device> getDevicesByPage(int pageNumber) {
            requestedPages.add(pageNumber);
            int currentCall = callCount.incrementAndGet();

            if (currentCall <= 4) {
                firstBatchStarted.countDown();
                try {
                    assertTrue(firstBatchStarted.await(5, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                return List.of(device(pageNumber + 1));
            }

            return List.of();
        }

        @Override
        public void resetPageNumber() {
        }

        @Override
        public boolean supportsParallelPageLoading() {
            return true;
        }

        @Override
        public int pageStep() {
            return 29;
        }
    }
}

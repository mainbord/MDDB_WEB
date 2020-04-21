package com.mddb.service;

import com.mddb.dao.DeviceRepository;
import com.mddb.domain.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class DbServicesImplTest {

    @Mock
    private DeviceRepository repository;

    @InjectMocks
    private DbServicesImpl service;

    @Test
    public void getDevice() {
        Long deviceId = 1L;
        Device expectedResponse = Device.builder().build();
        when(repository.findById(deviceId)).thenReturn(Optional.ofNullable(expectedResponse));
        Device response = service.getDevice(deviceId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void getDevices() {
        Device [] devices = {Device.builder().build(), Device.builder().build(), Device.builder().build()};
        List<Device> expectedResponse = Arrays.asList(devices);
        when(repository.findAll()).thenReturn(expectedResponse);
        List<Device> response = service.getDevices();
        assertEquals(3, response.size());
    }
}
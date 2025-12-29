package com.mddb.usecase;

import com.mddb.dao.DeviceRepository;
import com.mddb.dao.DeviceRepositorySetter;
import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import com.mddb.mapper.DeviceMapper;
import com.mddb.usecase.impl.DeviceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

    @Mock
    private DeviceRepository repository;
    @Mock
    private DeviceRepositorySetter deviceRepositorySetter;
    @Mock
    private DeviceMapper mapper;

    @InjectMocks
    private DeviceServiceImpl service;


    @Test
    public void testGetDevice() {
        Long deviceId = 1L;
        Device expectedResponse = Device.builder().build();
        when(deviceRepositorySetter.findById(deviceId)).thenReturn(Optional.ofNullable(expectedResponse));
        Device response = service.getDevice(deviceId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetDevices() {
        int pageNumber = 1;
        int pageSize = 10;
        Device[] devices = {Device.builder().build(), Device.builder().build(), Device.builder().build()};
        List<Device> expectedResponse = Arrays.asList(devices);
        PageRequest request = PageRequest.of(pageNumber, pageSize);
        PageImpl<Device> page = new PageImpl<>(expectedResponse);
        when(repository.findAll(request)).thenReturn(page);
        List<DeviceDto> response = service.getDevices(pageNumber, pageSize);
        assertEquals(3, response.size());
    }
}
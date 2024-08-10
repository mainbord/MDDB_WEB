package com.mddb.mapper;

import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapperImpl implements DeviceMapper{
    @Override
    public DeviceDto map(Device device) {
        if (device == null) return null;
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(device.getId());
        deviceDto.setModelName(device.getModelName());
        return deviceDto;
    }
}

package com.mddb.mapper;

import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    DeviceDto map(Device device);
}

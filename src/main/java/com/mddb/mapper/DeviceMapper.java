package com.mddb.mapper;

import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;

import java.util.Map;
//import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface DeviceMapper {
    DeviceDto map(Device device);

    Device mapParamsToDevice(Map<String, String> params, Integer url);
}

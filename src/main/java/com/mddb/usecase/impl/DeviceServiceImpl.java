package com.mddb.usecase.impl;

import com.mddb.dao.DeviceRepository;
import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import com.mddb.mapper.DeviceMapper;
import com.mddb.usecase.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mainbord on 20.09.17.
 */
@Service
@Log4j2(topic = "app")
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository repository;
    private final DeviceMapper deviceMapper;

    @Override
    public List<DeviceDto> getDevices(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable).stream()
                .map(deviceMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getCompaniesNames() {
        return repository.findAll().stream()
                .map(Device::getCompanyName)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Integer, String> getDevicesByCompany(String company) {
        return repository.findByCompanyName(company).stream()
                .collect(Collectors.toMap(Device::getId, Device::getModelName));
    }


    @Override
    public Device getDevice(Long id) {
        return repository.findById(id).orElse(null);
    }

}
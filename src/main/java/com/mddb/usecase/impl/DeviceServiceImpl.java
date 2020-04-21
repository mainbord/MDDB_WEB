package com.mddb.usecase.impl;

import com.mddb.dao.DeviceRepository;
import com.mddb.domain.Device;
import com.mddb.usecase.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @Override
    public List<Device> getDevices() {
        return repository.findAll();

    }

    @Override
    public Set<String> getCompaniesNames() {
        return repository.findAll().stream()
                .map(Device::getCompanyName)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Long, String> getDevicesByCompany(String company) {
        return repository.findByCompanyName(company).stream()
                .collect(Collectors.toMap(Device::getId, Device::getModelName));
    }


    @Override
    public Device getDevice(Long id) {
        return repository.findById(id).orElse(null);
    }

}
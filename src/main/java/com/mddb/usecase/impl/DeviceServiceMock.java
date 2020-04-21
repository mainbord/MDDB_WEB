package com.mddb.usecase.impl;

import com.mddb.dao.MockRepository;
import com.mddb.domain.Device;
import com.mddb.usecase.DeviceService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mainbord on 07.11.17.
 */
public class DeviceServiceMock implements DeviceService {

    private final MockRepository repository = new MockRepository();

    @Override
    public List<Device> getDevices() {
        return repository.findAll();
    }

    @Override
    public Set<String> getCompaniesNames() {
        return repository.getCompaniesNames();
    }

    @Override
    public Map<Long, String> getDevicesByCompany(String company) {
        return repository.findByCompanyName(company);
    }

    @Override
    public Device getDevice(Long id) {
        return repository.getDevice(id);
    }

}

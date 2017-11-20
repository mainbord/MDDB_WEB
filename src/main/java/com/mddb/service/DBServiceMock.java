package com.mddb.service;

import com.mddb.dao.MockRepository;
import com.mddb.domain.Device;

import java.util.Map;
import java.util.Set;

/**
 * Created by mainbord on 07.11.17.
 */
public class DBServiceMock implements DbService {

    private final MockRepository repository = new MockRepository();

    @Override
    public Iterable<Device> getDevices() {
        return repository.findAll();
    }

    @Override
    public Set<String> getCompaniesNames() {
        return repository.getCompaniesNames();
    }

    @Override
    public Map<Integer, String> getDevicesByCompany(String company) {
        return repository.findByCompanyName(company);
    }

    @Override
    public Device getDevice(Integer id) {
        return repository.getDevice(id);
    }

    @Override
    public int saveDevices() {
        return 0;
    }
}

package com.mddb.service;

import com.mddb.DAO.DeviceRepository;
import com.mddb.domain.Cpu;
import com.mddb.domain.Device;
import com.mddb.domain.Display;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by mainbord on 20.09.17.
 */
@Service
public class DbServicesImpl implements DbService {

    private final DeviceRepository repository;

    @Autowired
    public DbServicesImpl(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Device> getDevices() {
        return repository.findAll();

    }

    @Override
    public Set<String> getCompaniesNames() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        repository.findAll().iterator(),
                        Spliterator.ORDERED),
                 false)
                .map(Device::getCompanyName)
                .collect(Collectors.toCollection(() -> new TreeSet<String>(Comparator.naturalOrder())));
    }

    @Override
    public Map<Integer, String> getDevicesByCompany(String company) {
        repository.findAll();

        return repository.findByCompanyName(company).stream()
                .collect(Collectors.toMap(Device::getId, Device::getModelName));
    }


    @Override
    public Device getDevice(Integer id) {
        for (Device dev :
                getDevices()) {
            if (dev.getId() == id){
                return dev;
            }
        }
        return null;
    }
}

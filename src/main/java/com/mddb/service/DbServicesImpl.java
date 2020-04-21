package com.mddb.service;

import com.mddb.dao.DeviceRepository;
import com.mddb.domain.Device;
import com.mddb.loader.DbLoader;
import com.mddb.loader.PdaDbLoader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by mainbord on 20.09.17.
 */
@Service
@Log4j2(topic = "app")
public class DbServicesImpl implements DbService {

    private final DeviceRepository repository;

    @Autowired
    public DbServicesImpl(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Device> getDevices() {
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
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.naturalOrder())));
    }

    @Override
    public Map<Long, String> getDevicesByCompany(String company) {
        repository.findAll();

        return repository.findByCompanyName(company).stream()
                .collect(Collectors.toMap(Device::getId, Device::getModelName));
    }


    @Override
    public Device getDevice(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public int saveDevices() {
        DbLoader loader = new PdaDbLoader();
        loader.getUrlDevices();
        for (Device dev :
                loader.getDevices()) {
            repository.save(dev);
        }
        log.info("ALL devices saved");
        return 0;
    }
}
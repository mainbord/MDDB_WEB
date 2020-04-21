package com.mddb.usecase.impl;

import com.mddb.client.DeviceClient;
import com.mddb.dao.DeviceRepository;
import com.mddb.usecase.DeviceLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "app")
public class LoaderServiceImpl implements DeviceLoader {

    private final DeviceRepository repository;
    private final DeviceClient deviceClient;

    @Override
    public void loadDevices() {
        repository.saveAll(deviceClient.getDevices());
        log.info("ALL devices saved");
    }
}

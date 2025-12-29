package com.mddb.usecase.impl;

import com.mddb.client.DeviceClient;
import com.mddb.dao.DeviceRepository;
import com.mddb.dao.DeviceRepositorySetter;
import com.mddb.domain.Device;
import com.mddb.usecase.DeviceLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoaderServiceImpl implements DeviceLoader {

    private final DeviceRepository repository;
    private final DeviceClient deviceClient;
    private final DeviceRepositorySetter deviceRepositorySetter;

    private final int pageSize = 100;

    @Override
    @Async
    public void loadDevices() {
        log.info("loadDevices started");
        try {
            while (true) {
                List<Device> devices = deviceClient.getDevicesWithPaging();
                if (devices.size() == 0) {
                    break;
                }
                List<Device> existingDevices = repository.findByIdPhonedbIn(devices.stream().map(d -> d.getIdPhonedb()).toList());
                if (existingDevices.size() == 0) {
                    deviceRepositorySetter.saveAll(devices);
                    log.info("saves devices count {}", devices.size());
                }
            }

        } finally {
            deviceClient.resetPageNumber();
        }

        log.info("ALL devices saved");
    }
}

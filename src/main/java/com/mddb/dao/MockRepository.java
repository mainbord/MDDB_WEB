package com.mddb.dao;

import com.mddb.domain.Device;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * Created by mainbord on 07.11.17.
 */
public class MockRepository {

    private static List<Device> devices = new CopyOnWriteArrayList<>();

    static {
        devices.add(new Device(
                1, null, null, "Motorola", "Droid Mini", null, null, null, null, null, null, null,
                null, null, null, null, null, "MSM8960DT", 2, "ARM", "1700L", "ARMV7", 28, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, "4.3", "720P", "LCD", null, 16, null, 10, "Gorilla 3",
                true, true, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null
        ));

        devices.add(new Device(
                2, null, null, "Samsung", "Galaxy S7", null, null, null, null, null, null, null,
                null, null, null, null, null, "Exynos", 2, "ARM", "1700", "ARMV7", 28, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, "5.1", "4K", "AMOLED", null, 16, null, 10, "Gorilla 5",
                true, true, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null
        ));

        devices.add(new Device(
                3, null, null, "Sony", "XZ1 Compact", null, null, null, null, null, null, null,
                null, null, null, null, null, "Qualcomm Snapdragon 835 MSM8998", 2, "ARM", "1700", "ARMV7", 28, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, "4.6", "720P", "LCD", null, 16, null, 10, "Gorilla 5",
                true, true, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null
        ));
    }

    public Map<Integer, String> findByCompanyName(String company){
        return devices.stream()
                .filter(dev -> dev.companyName().equals(company))
                .collect(Collectors.toMap(Device::id, Device::modelName));
    }

    public List<Device> findAll(){
        return devices;
    }

    public Set<String> getCompaniesNames(){
        return devices.stream()
                .map(Device::companyName)
                .collect(Collectors.toSet());
    }

    public Device getDevice(Long id){
        if (isNull(id)) return null;
        for (Device dev :
                devices) {
            if (dev.id().equals(id)){
                return dev;
            }
        }
        return null;
    }
}

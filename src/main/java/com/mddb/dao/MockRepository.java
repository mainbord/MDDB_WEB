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
        Device device = new Device();
        device.setId(1L);
        device.setCompanyName("Motorola");
        device.setModelName("Droid Mini");
//        device.setCpu(new Cpu(2, "ARM", 1700, "ARMV7", 28));
        device.setNumberOfCores(2);
        device.setArchitecture("ARM");
        device.setMaxFrequencyPerCore("1700L");
        device.setInstruction("ARMV7");
        device.setManufacturingMethod(28);
        device.setSoc("MSM8960DT");
//        Display display = new Display();
        device.setDisplayColorAmount(16);
        device.setDisplayDiagonal("4.3");
        device.setDisplayGlareFilter(true);
        device.setDisplayOleophobic(true);
        device.setDisplayGlassDescription("Gorilla 3");
        device.setDisplayMultitouch(10);
        device.setDisplayType("LCD");
        device.setDisplayResolution("720P");
        devices.add(device);

        device = new Device();
        device.setId(2L);
        device.setCompanyName("Samsung");
        device.setModelName("Galaxy S7");
//        device.setCpu(new Cpu(8, "ARM*", 2400, "ARMV8A", 10));
        device.setNumberOfCores(2);
        device.setArchitecture("ARM");
        device.setMaxFrequencyPerCore("1700");
        device.setInstruction("ARMV7");
        device.setManufacturingMethod(28);
        device.setSoc("MSM8960DT");
        device.setSoc("Exynos");
//        display = new Display();
        device.setDisplayColorAmount(16);
        device.setDisplayDiagonal("5.1");
        device.setDisplayGlareFilter(true);
        device.setDisplayOleophobic(true);
        device.setDisplayGlassDescription("Gorilla 5");
        device.setDisplayMultitouch(10);
        device.setDisplayType("AMOLED");
        device.setDisplayResolution("4K");
        devices.add(device);

        device = new Device();
        device.setId(3L);
        device.setCompanyName("Sony");
        device.setModelName("XZ1 Compact");
//        device.setCpu(new Cpu(8, "ARM", 2500, "ARMV7", 9));
        device.setNumberOfCores(2);
        device.setArchitecture("ARM");
        device.setMaxFrequencyPerCore("1700");
        device.setInstruction("ARMV7");
        device.setManufacturingMethod(28);
        device.setSoc("MSM8960DT");
        device.setSoc("Qualcomm Snapdragon 835 MSM8998");
//        display = new Display();
        device.setDisplayColorAmount(16);
        device.setDisplayDiagonal("4.6");
        device.setDisplayGlareFilter(true);
        device.setDisplayOleophobic(true);
        device.setDisplayGlassDescription("Gorilla 5");
        device.setDisplayMultitouch(10);
        device.setDisplayType("LCD");
        device.setDisplayResolution("720P");
        devices.add(device);
    }

    public Map<Long, String> findByCompanyName(String company){
        return devices.stream()
                .filter(dev -> dev.getCompanyName().equals(company))
                .collect(Collectors.toMap(Device::getId, Device::getModelName));
    }

    public List<Device> findAll(){
        return devices;
    }

    public Set<String> getCompaniesNames(){
        return devices.stream()
                .map(Device::getCompanyName)
                .collect(Collectors.toSet());
    }

    public Device getDevice(Long id){
        if (isNull(id)) return null;
        for (Device dev :
                devices) {
            if (dev.getId().equals(id)){
                return dev;
            }
        }
        return null;
    }
}

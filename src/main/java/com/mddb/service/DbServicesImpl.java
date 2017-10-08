package com.mddb.service;

import com.mddb.domain.Cpu;
import com.mddb.domain.Device;
import com.mddb.domain.Display;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by mainbord on 20.09.17.
 */
@Service
public class DbServicesImpl implements DbService {

    //data for testing
    private static List<Device> devices = new CopyOnWriteArrayList<>();
    private static Set<String> companiesNames = new HashSet<>();

    static {
        Device device = new Device();
        device.setId(1);
        device.setCompanyName("Motorola");
        device.setModelName("Droid Mini");
        device.setCpu(new Cpu(2, "ARM", 1700, "ARMV7", 28));
        device.setSoc("MSM8960DT");
        Display display = new Display();
        display.setColorAmount(16);
        display.setDiagonal("4.3");
        display.setGlareFilter(true);
        display.setOleophobic(true);
        display.setGlassDescription("Gorilla 3");
        display.setMultitouch(10);
        display.setType(Display.displayType.LCD);
        display.setResolution("720P");
        devices.add(device);

        device = new Device();
        device.setId(2);
        device.setCompanyName("Samsung");
        device.setModelName("Galaxy S7");
        device.setCpu(new Cpu(8, "ARM*", 2400, "ARMV8A", 10));
        device.setSoc("Exynos");
        display = new Display();
        display.setColorAmount(16);
        display.setDiagonal("5.1");
        display.setGlareFilter(true);
        display.setOleophobic(true);
        display.setGlassDescription("Gorilla 5");
        display.setMultitouch(10);
        display.setType(Display.displayType.AMOLED);
        display.setResolution("4K");
        devices.add(device);

        device = new Device();
        device.setId(3);
        device.setCompanyName("Sony");
        device.setModelName("XZ1 Compact");
        device.setCpu(new Cpu(8, "ARM", 2500, "ARMV7", 9));
        device.setSoc("Qualcomm Snapdragon 835 MSM8998");
        display = new Display();
        display.setColorAmount(16);
        display.setDiagonal("4.6");
        display.setGlareFilter(true);
        display.setOleophobic(true);
        display.setGlassDescription("Gorilla 5");
        display.setMultitouch(10);
        display.setType(Display.displayType.LCD);
        display.setResolution("720P");
        devices.add(device);

        companiesNames = devices.stream()
                .map(Device::getCompanyName)
                .collect(Collectors.toCollection(() -> new TreeSet<String>(Comparator.naturalOrder())));
    }


    @Override
    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public Set<String> getCompaniesNames() {
        return companiesNames;
    }

    @Override
    public Map<Integer, String> getDevicesByCompany(String company) {
        return devices.stream()
                .filter(dev -> dev.getCompanyName().equals(company))
//                .collect(toMap(Entry::getKey, Entry::getValue, (v1, v2) -> merge(v1, v2), TreeMap::new));
//                .sorted(Comparator.comparing(Device::getModelName))
//                .collect(Collectors.toMap(x -> x.getId(), x-> x.getModelName()))
//                .collect(Collectors.toMap(new Supplier<Map<Integer, String>>() {
//                    @Override
//                    public Map<Integer, String> get() {
//                        return new TreeMap<Integer, String>(Comparator.naturalOrder());
//                    }
//                }))
//                .collect(Collectors.toCollection(() -> new TreeMap<Integer, String>(Comparator.naturalOrder())));
                .collect(Collectors.toMap(Device::getId, Device::getModelName));
    }


    @Override
    public Device getDevice(Integer id) {
        for (Device dev :
                devices) {
            if (dev.getId() == id){
                return dev;
            }
        }
        return null;
    }
}

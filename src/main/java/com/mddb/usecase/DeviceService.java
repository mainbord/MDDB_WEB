package com.mddb.usecase;

import com.mddb.domain.Device;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mainbord on 20.09.17.
 */
public interface DeviceService {

    List<Device> getDevices();

    Set<String> getCompaniesNames();

    Map<Long, String> getDevicesByCompany(String company);

    Device getDevice(Long id);

}

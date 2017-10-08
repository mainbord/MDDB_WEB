package com.mddb.service;

import com.mddb.domain.Device;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by mainbord on 20.09.17.
 */
public interface DbService {

    public List<Device> getDevices();

    public Set<String> getCompaniesNames();

    public Map<Integer, String> getDevicesByCompany(String company);

    public Device getDevice(Integer id);
}

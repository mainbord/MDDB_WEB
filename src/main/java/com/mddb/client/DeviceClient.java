package com.mddb.client;

import com.mddb.domain.Device;

import java.util.List;

public interface DeviceClient {
    List<Device> getDevices();
}

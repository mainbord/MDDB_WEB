package com.mddb.client;

import com.mddb.domain.Device;

import java.util.List;

public interface DeviceClient {

    List<Device> getDevicesWithPaging();

    void resetPageNumber();

    default boolean supportsParallelPageLoading() {
        return false;
    }

    default int pageStep() {
        return 1;
    }

    default List<Device> getDevicesByPage(int pageNumber) {
        return getDevicesWithPaging();
    }
}
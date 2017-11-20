package com.mddb.loader;

import com.mddb.domain.Device;

import java.util.List;
import java.util.Set;

/**
 * Created by mainbord on 14.11.17.
 */
public interface DbLoader {

    public List<Device> getDevices();

    public Set<String> getUrlDevices();
}

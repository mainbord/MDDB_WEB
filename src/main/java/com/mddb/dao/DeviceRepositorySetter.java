package com.mddb.dao;

import com.mddb.domain.Device;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by mainbord on 29.10.17.
 */
public interface DeviceRepositorySetter extends CrudRepository<Device, Long>, JpaSpecificationExecutor<Device> {

}

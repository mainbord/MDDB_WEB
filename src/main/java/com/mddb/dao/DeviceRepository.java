package com.mddb.dao;

import com.mddb.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by mainbord on 29.10.17.
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByReleaseDate(Date date);

    List<Device> findByCompanyName(String companyName);

    /*    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);*/
}

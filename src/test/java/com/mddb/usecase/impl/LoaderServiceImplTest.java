package com.mddb.usecase.impl;

import com.mddb.dao.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SpringBootTest(classes = {DeviceRepository.class, CrudRepository.class})
//@DataJpaTest todo do not work with spring boot 4
class LoaderServiceImplTest {

//    @Autowired
//    DeviceRepository repository;

    @Test
    void loadDevices() {
//        repository.findByIdphonedb(List.of(1));
    }
}
package com.mddb.controller;

import com.mddb.domain.Device;
import com.mddb.service.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by mainbord on 20.09.17.
 */

@Log4j2(topic = "app")
@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class MainController {

    private final DbService service;

    @GetMapping
    public String getString(Locale locale) {
        return "This is MDDB";
    }


    @GetMapping(value = "/phones")
    public Callable<Iterable<Device>> getDevices(Locale locale) {
        log.info("getDevices");
        return service::getDevices;
    }


}

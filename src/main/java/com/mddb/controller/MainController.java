package com.mddb.controller;

import com.mddb.domain.Device;
import com.mddb.usecase.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * Created by mainbord on 20.09.17.
 */

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class MainController {

    private final DeviceService service;

    @GetMapping(value = "/phones")
    public List<Device> getDevices(Locale locale) {
        return service.getDevices();
    }


}

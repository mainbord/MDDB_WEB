package com.mddb.controller;

import com.mddb.usecase.DeviceLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/loaders")
@RequiredArgsConstructor
public class DeviceLoaderController {

    private final DeviceLoader loader;

    @PostMapping("/devices")
    public void loadDevices(){
        loader.loadDevices();
    }
}

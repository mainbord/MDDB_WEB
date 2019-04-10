package com.mddb.controller;

import com.mddb.domain.Device;
import com.mddb.service.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2(topic = "app")
public class WebController {

    private final DbService service;

    @RequestMapping(value = "/index",
            method = RequestMethod.GET)
    public String index(Map<String, Object> model, Locale locale) {
        try {
            model.put("companies", service.getCompaniesNames());
            return "index";
        } catch (Exception e) {
            log.error(e);
            return "error";
        }
    }


    @RequestMapping(value = "/phones/{companyname}",
            method = RequestMethod.GET)
    public String getDevicesByCompany(
            @PathVariable String companyname,
            Map<String, Object> model,
            Locale locale) {
        log.info("getDevices");
        model.put("devices", service.getDevicesByCompany(companyname));
        return "devices";
    }

    @RequestMapping(value = "/device/{id}",
            method = RequestMethod.GET)
    public String getDevice(
            @PathVariable Integer id,
            Map<String, Object> model,
            Locale locale) {
        Device dev = service.getDevice(id);
        model.put("device", dev);
        log.info(dev.toString());
        return "oneDevice";
    }
}

package com.mddb.controller;

import com.mddb.domain.Device;
import com.mddb.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by mainbord on 20.09.17.
 */

@Slf4j
@Controller
@RequestMapping(value = "/")
public class MainController {

    /*История изменений:
    09.10.17 - Добавлены header и footer ко всем страница, дописана страничка с одним устройством
    * */

    /*Развитие проекта:
    1) доделать форму отображения одного устройства
    2) доделать поиск
    3) Подключить к программе базу данных
    4) написать загрузчик из пдадиби, с фопда, с devicespecifications и с яндекс маркета.
    5) добавить форму добавления на джаве
    6) добавить форму добавления на ajax то есть без перезагрузки страницы.
    7) Добавить мультиязычность
    8) добавить header и footer отдельными файлами.
    *
    * */

    @Autowired
    DbService service;

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
    public String getString() {
        return "This is MDDB";
    }


    @RequestMapping(value = "/phones",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Callable<List<Device>> getDevices() {
        log.info("getDevices");
        return () -> service.getDevices();
    }

    @RequestMapping(value = "/phones/{companyname}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDevicesByCompany(
            @PathVariable String companyname,
            Map<String, Object> model) {
        log.info("getDevices");
        model.put("devices", service.getDevicesByCompany(companyname));
        return "devices";
    }

    @RequestMapping(value = "/index",
            method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("companies", service.getCompaniesNames());
        return "index";
    }

    @RequestMapping(value = "/device/{id}",
            method = RequestMethod.GET)
    public String getDevice(
            @PathVariable Integer id,
            Map<String, Object> model) {
        model.put("device", service.getDevice(id));
        return "oneDevice";
    }
}

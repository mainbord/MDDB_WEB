package com.mddb.controller;

import com.mddb.domain.Device;
import com.mddb.dto.DeviceDto;
import com.mddb.usecase.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

/**
 * Created by mainbord on 20.09.17.
 */

@RestController
@RequestMapping(value = "/")
//@RequestMapping(value = "/", headers = {"Access-Control-Allow-Origin: *"})
@RequiredArgsConstructor
public class MainController {

    private final DeviceService service;

//    @GetMapping(value = "/phones")
//    public List<DeviceDto> getDevices(Locale locale) {
//        return service.getDevices();
//    }

    @GetMapping(value = "/phones")
    public ResponseEntity<List<DeviceDto>> getDevices(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                                      @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(service.getDevices(pageNumber, pageSize));
    }
//
//    @GetMapping(value = "/phones")
//    public Flux<DeviceDto> getDevices(Locale locale) {
//        return Flux.fromStream(service.getDevices().stream());
//    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.controller;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.service.IDeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RestController
@RequestMapping("manager")
@Api(value = "Device Manager Web Services API", description = " API")
public class DeviceController {

    @Autowired
    private IDeviceService service;

    @PostMapping("/saveDevice")
    public ResponseEntity saveDevice(@RequestBody Device payload) {
        try {
            service.saveDevice(payload);
            return new ResponseEntity<String>(String.
                    format(" the device with name %s has been created successfully",
                            payload.getName()), HttpStatus.CREATED);
        } catch (DeviceManagerException ex) {
            return new ResponseEntity<String>(String.
                    format(ex.getMessage(),
                            payload.getName()), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/updateDevice")
    public ResponseEntity updateDevice(@RequestBody Device payload) {
        try {
            Device device = service.updateDevice(payload);
            return new ResponseEntity<Device>(device, HttpStatus.CREATED);
        } catch (DeviceManagerException ex) {
            return new ResponseEntity<String>(String.
                    format(ex.getMessage(),
                            payload.getName()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findAllDevices")
    public ResponseEntity findAllDevices() {
        List<Device> devices = service.findAll();
         ObjectMapper mapper = new ObjectMapper();
        String[] ignorableFieldNames = {"secretKey"};
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("jsonFilter1", SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        ObjectWriter writer = mapper.writer(filters);
        String list  = null;
        try {
            list = writer.writeValueAsString(devices);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DeviceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<String>(list, HttpStatus.OK);
    }

    @GetMapping("/findDevice")
    public ResponseEntity findDevice(@RequestParam(value = "secretKey", required = false) String secretKey) {
        Device device = service.findDevice(secretKey);
        return new ResponseEntity<Device>(device, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/findDeviceByStatus")
    public ResponseEntity findDeviceByStatus(@RequestParam(value = "status", required = false) String status)  {
        List<Device> findDeviceByStatus = service.findDeviceByStatus(status);
        ObjectMapper mapper = new ObjectMapper();
        String[] ignorableFieldNames = {"secretKey"};
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("jsonFilter1", SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        ObjectWriter writer = mapper.writer(filters);
        String list = null;
        try {
            list = writer.writeValueAsString(findDeviceByStatus);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DeviceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<String>(list, HttpStatus.OK);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.controller.DeviceController;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.service.IDeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceListingServiceTest {

    @Autowired
    private IDeviceService service;
    private Map<String, Device> devicesMap;
    private List<Device> devices;

    @Before
    public void createMockDevices() {
        String[] statuses = new String[]{"OK", "STALE", "NEW", "UNHEALTHY"};
        devicesMap = new HashMap();
        for (int i = 0; i < 5; i++) {
            String secretkey = String.valueOf(new Random().nextInt(5000));
            String status = statuses[new Random().nextInt(4)];
            devicesMap.put(secretkey,
                    new Device("johnson eyo", secretkey, status));
        }

        devices = devicesMap.
                entrySet().
                stream().
                map(map -> map.getValue()).
                collect(Collectors.toList());

    }

    @Test
    public void excludingSecretKeyFromList() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String[] ignorableFieldNames = {"secretKey"};
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("jsonFilter1", SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        ObjectWriter writer = mapper.writer(filters);
        String list = null;

        list = writer.writeValueAsString(devices);
        Assert.assertTrue(!list.contains("secretKey"));
        Assert.assertTrue(list.contains("name"));
        Assert.assertTrue(list.contains("status"));

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.service.IDeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class DeviceRetrievalServiceTest {

    @Autowired
    private IDeviceService service;
    private final String secretKey = "@42347890#42";
    private Device device;

    @Before
    public void preFillMap() throws DeviceManagerException {
        device = new Device();
        device.setName("johnson eyo");
        device.setSecretKey(secretKey);
        service.saveDevice(device);
    }

    @Test
    public void fetchDeviceBySecretKey() throws JsonProcessingException {
        Device foundDevice = service.findDevice(secretKey);
        Assert.assertEquals(foundDevice, device);
//        ObjectMapper mapper = new ObjectMapper();
//        String toString = mapper.writeValueAsString(foundDevice);
//        Assert.assertTrue(toString.contains("secretKey"));
    }

    @Test
    public void fetchDeviceByEmptySecretKey() {
        Device foundDevice = service.findDevice(null);
        Assert.assertNull(foundDevice);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.service.IDeviceService;
import org.junit.Assert;
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
public class DeviceUpdateServiceTest {

    @Autowired
    private IDeviceService service;

    @Test(expected = DeviceManagerException.class)
    public void updateDeviceWithEmptySecretKey() throws DeviceManagerException {
        Device device = new Device();
        device.setStatus("OK");
        service.updateDevice(device);
    }

    @Test(expected = DeviceManagerException.class)
    public void updateDeviceWithEmptyUpdateStatus() throws DeviceManagerException {
        Device device = new Device();
        device.setSecretKey("@456235945*7");
        service.updateDevice(device);
    }

    @Test(expected = DeviceManagerException.class)
    public void updateDeviceWithInvalidSecretKey() throws DeviceManagerException {
        Device device = new Device();
        device.setName("jane doe");
        device.setSecretKey("#24653@3535");
        service.saveDevice(device);
        device.setStatus("OK");
        //invalid key
        device.setSecretKey("@3445234");
        service.updateDevice(device);
    }

    @Test
    public void updateDeviceWithValidSecretKeyAndValidUpdateStatus() throws DeviceManagerException {
        Device device = new Device();
        device.setName("jane doe");
        device.setSecretKey("#24653@3535");
        service.saveDevice(device);
        device.setStatus("OK");
        // valid key
        device.setSecretKey("#24653@3535");
        Device d = service.updateDevice(device);
        Assert.assertEquals(device.getStatus(), d.getStatus());
    }

    @Test(expected = DeviceManagerException.class)
    public void updateDeviceWithValidSecretKeyAndInValidUpdateStatus() throws DeviceManagerException {
        Device device = new Device();
        device.setName("jane doe");
        device.setSecretKey("#24653@3535");
        service.saveDevice(device);
        device.setStatus("NOT_OK");
        // valid key
        device.setSecretKey("#24653@3535");
        service.updateDevice(device);
    }

}

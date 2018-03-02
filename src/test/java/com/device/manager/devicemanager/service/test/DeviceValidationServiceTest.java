/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.service.DeviceValidationServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class DeviceValidationServiceTest {
    
   
    @Autowired
    private DeviceValidationServiceImpl impl;
    
    @Test(expected = DeviceManagerException.class)
    public void deviceRequestWithEmptyFields() throws DeviceManagerException {
        Device device = new Device();
        impl.validateDeviceRequest(device);     
    }

    @Test(expected = DeviceManagerException.class)
    public void deviceRequestWithHumanFriendlyNameButNoSecretKey() throws DeviceManagerException {
        Device device = new Device();
        device.setName("johnson eyo");
        impl.validateDeviceRequest(device);     
    }
    
    @Test(expected = DeviceManagerException.class)
    public void deviceRequestWithNoNameButSecretKey() throws DeviceManagerException {
        Device device = new Device();
        device.setSecretKey("345634#@3535");
        impl.validateDeviceRequest(device);     
    }

}

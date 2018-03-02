/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.service.IDeviceService;
import java.util.List;
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
public class DeviceRegistrationServiceTest {
    
    @Autowired private IDeviceService service;
    
    @Test(expected = DeviceManagerException.class)
    public void registerDeviceWithOnlyName() throws DeviceManagerException{
        Device device  = new Device();
        device.setName("johnson eyo");
        service.saveDevice(device);    
    }
    
    @Test(expected = DeviceManagerException.class)
    public void registerDeviceWithOnlySecretKey() throws DeviceManagerException{
        Device device  = new Device();
        device.setSecretKey("43563@24545");
        service.saveDevice(device);    
    }
    
    @Test
    public void register1DeviceWithValidFields() throws DeviceManagerException{
        Device device  = new Device();
        device.setName("johnson eyo");
        device.setSecretKey("43563@24545");
        service.saveDevice(device); 
        List<Device> findAll = service.findAll();
        Assert.assertTrue(0<findAll.size());
    }
    
    @Test
    public void register2ExtraDeviceWithValidFields() throws DeviceManagerException{
        Device device  = new Device();
        device.setName("johnson eyo");
        device.setSecretKey("43563@24545");
        service.saveDevice(device); 
        device.setName("jane doe");
        device.setSecretKey("767@3535355");
        service.saveDevice(device); 
        List<Device> findAll = service.findAll();
        Assert.assertTrue(findAll.size()>1);
    }
    
    
    
    
    
}

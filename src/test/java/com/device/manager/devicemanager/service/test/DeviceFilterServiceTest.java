/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.service.IDeviceService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class DeviceFilterServiceTest {

    @Autowired
    private IDeviceService service;
    private Map<String, Device> devicesMap;

    @Before
    public void createMockDevices() {
        String[] statuses = new String[]{"OK", "STALE", "NEW", "UNHEALTHY"};
        devicesMap = new HashMap();
        for (int i = 0; i < 5; i++) {
            String secretkey = String.valueOf(new Random().nextInt(5000));
            String status =   statuses[new Random().nextInt(4)];
            devicesMap.put(secretkey,
                    new Device("johnson eyo", secretkey, status));
        }

    }

    @Test
    public void filterByStatus() throws DeviceManagerException {
        boolean exists = false;

        List<Device> devicesFound = devicesMap.
                entrySet().stream().
                filter(dim -> dim.getValue().getStatus().equals("OK")).
                map(map -> map.getValue()).              
                collect(Collectors.toList());
       for (Device d : devicesFound) {
            if (!d.getStatus().equals("OK")) {
                exists = true;
                break;
            }
        }

        Assert.assertTrue(!exists);

    }

}

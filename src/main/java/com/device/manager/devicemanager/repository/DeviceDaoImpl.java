/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.repository;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.StaleUpdateTask;
import com.device.manager.devicemanager.model.Device;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public class DeviceDaoImpl implements IDeviceDao {

    private Map<String, Device> devicesMap;
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @PostConstruct
    public void initMapStore() {
        devicesMap = new HashMap<String, Device>();
    }

    @Override
    public void saveDevice(Device d) {
        devicesMap.put(d.getSecretKey(), d);
    }

    @Override
    public List<Device> findAllDevices() {
        List<Device> devices = new ArrayList();
        devicesMap.entrySet().forEach(dim -> {
            devices.add(dim.getValue());
        });
        return devices;
    }

    @Override
    public Device updateDevice(Device payload) throws DeviceManagerException {
        if (!deviceExists(payload.getSecretKey())) {
            throw new DeviceManagerException("secret key doest exist ");
        }
        Device d = devicesMap.get(payload.getSecretKey());
        d.setStatus(payload.getStatus());
        Device put = devicesMap.put(payload.getSecretKey(), d);
        if (put.getStatus().equals("OK")) {
            taskScheduler.schedule(new StaleUpdateTask(put, devicesMap),
                    new Date(System.currentTimeMillis() + 60000 * 5));
        }
        return put;
    }

    private boolean deviceExists(String secretKey) {
        return devicesMap.containsKey(secretKey);
    }

    @Override
    public List<Device> findDeviceByStatus(String status) {
        return devicesMap
                .entrySet()
                .stream()
                .filter(map -> status.equals(map.getValue().getStatus()))
                .map(map -> map.getValue()).collect(Collectors.toList());
    }

    @Override
    public Device findDevice(String secretKey) {
        return devicesMap.get(secretKey);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.repository.IDeviceDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceDao deviceDao;
    @Autowired
    private INameChecker checker;
    @Autowired
    private IDeviceValidationService emptyFieldCheck;

    @Override
    public void saveDevice(Device device) throws DeviceManagerException {
        emptyFieldCheck.validateDeviceRequest(device);
        if (!checker.isHumanFriendly(device.getName())) {
            throw new DeviceManagerException("device name is not human friendly");
        }
        device.setStatus("NEW");
        deviceDao.saveDevice(device);
    }

    @Override
    public List<Device> findAll() {
        return deviceDao.findAllDevices();
    }

    @Override
    public Device updateDevice(Device payload) throws DeviceManagerException {
        if (payload.getSecretKey() == null) {
            throw new DeviceManagerException("please provide a secret key");
        }
        if (payload.getStatus() == null) {
            throw new DeviceManagerException("please provide an update status");
        }
        if (payload.getStatus().equals("OK") | payload.getStatus().equals("UNHEALTHY")) {
            return deviceDao.updateDevice(payload);
        }

        throw new DeviceManagerException("device status can be either ok or unhealthy");

    }

    @Override
    public List<Device> findDeviceByStatus(String status) {
        return this.deviceDao.findDeviceByStatus(status);
    }

    @Override
    public Device findDevice(String secretKey) {
        return deviceDao.findDevice(secretKey);
    }

}

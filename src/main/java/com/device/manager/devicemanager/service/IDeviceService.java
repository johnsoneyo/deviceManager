/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import java.util.List;

/**
 *
 * @author johnson3yo
 */
public interface IDeviceService {

    void saveDevice(Device device) throws DeviceManagerException;
    List<Device> findAll();
    Device updateDevice(Device payload) throws DeviceManagerException;
    List<Device> findDeviceByStatus(String status);
    Device findDevice(String secretKey);
    
}

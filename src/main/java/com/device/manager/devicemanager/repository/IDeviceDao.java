/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.repository;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author johnson3yo
 */
public interface IDeviceDao {
    void saveDevice(Device device);
    List<Device> findAllDevices();
    Device updateDevice(Device payload)throws DeviceManagerException;
    List<Device> findDeviceByStatus(String status);
    Device findDevice(String secretKey);
}

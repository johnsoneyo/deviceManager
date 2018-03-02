/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;

/**
 *
 * @author johnson3yo
 */
public interface IDeviceValidationService {
    
     void validateDeviceRequest(Device device)throws DeviceManagerException;
    
}

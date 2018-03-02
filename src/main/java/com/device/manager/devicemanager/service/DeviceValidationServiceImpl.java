/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.validation.InputRequired;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class DeviceValidationServiceImpl implements IDeviceValidationService {

    @Override
    public void validateDeviceRequest(Device device) throws DeviceManagerException {
        Field[] fields = device.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                if (f.isAnnotationPresent(InputRequired.class)) {
                    String value = (String) f.get(device);
                    if (value == null || value.length() == 0) {
                        throw new DeviceManagerException(String.format("The field %s is empty", f.getName()));
                    }
                }
            }

        } catch (IllegalArgumentException ex) {
            throw new DeviceManagerException(ex.getMessage());
        } catch (IllegalAccessException ex) {
            throw new DeviceManagerException(ex.getMessage());
        }

    }

}

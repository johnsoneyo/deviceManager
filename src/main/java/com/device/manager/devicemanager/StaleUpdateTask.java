/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager;

import com.device.manager.devicemanager.model.Device;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author johnson3yo
 */
public class StaleUpdateTask implements Runnable {

    private Device message;
    private Map<String, Device> dataMap;

    public StaleUpdateTask(Device message, Map<String, Device> dataMap) {
        this.message = message;
        this.dataMap = dataMap;
    }

    @Override
    public void run() {
        Device update = dataMap.get(message.getSecretKey());
        if (update.getStatus().equals("OK")) {
            update.setStatus("STALE");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.model;

import com.device.manager.devicemanager.validation.InputRequired;
import com.fasterxml.jackson.annotation.JsonFilter;

/**
 *
 * @author johnson3yo
 */


@JsonFilter("jsonFilter1") 
public class Device {
    
    @InputRequired private String name;
    @InputRequired private String secretKey;
    private String status;

    public Device(String name, String secretKey, String status) {
        this.name = name;
        this.secretKey = secretKey;
        this.status = status;
    }

    public Device() {
    }
    
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.DeviceManagerException;
import com.device.manager.devicemanager.model.Device;
import com.device.manager.devicemanager.validation.InputRequired;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InputRequiredAnotTest {
    
    
    @Test
    public void inputRequiredFor2MandatoryFields(){
         Field[] fields = Device.class.getDeclaredFields();
         int mandatoryCount = 0;       
            for (Field f : fields) {
                f.setAccessible(true);
                if(f.isAnnotationPresent(InputRequired.class)){
                    mandatoryCount++;
                }
            }
           Assert.assertTrue(mandatoryCount==2);
        
    }
    
}

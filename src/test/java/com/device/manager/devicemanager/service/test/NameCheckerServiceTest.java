/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service.test;

import com.device.manager.devicemanager.service.INameChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NameCheckerServiceTest {

    @Autowired
    private INameChecker checker;

    @Test
    public void namePassedWithoutSpecialCharacters() {
        boolean isValid = checker.isHumanFriendly("johnson eyo");
        Assert.assertTrue(isValid);
    }

    @Test
    public void namePassedwithSpecialCharacters() {
        boolean isValid = checker.isHumanFriendly("johnson j. eyo");
        Assert.assertFalse(isValid);
    }

    @Test
    public void namePassedAsGibberish() {
        boolean isValid = checker.isHumanFriendly("jwerwdffdyo");
        Assert.assertTrue(isValid);
    }
    
     @Test
    public void nameAsSpecialCharacters() {
        boolean isValid = checker.isHumanFriendly("@#$%&*&johnson!@~#@");
        Assert.assertFalse(isValid);
    }

}

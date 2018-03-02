/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.device.manager.devicemanager.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class NameCheckerImpl implements INameChecker {

    @Override
    public boolean isHumanFriendly(String name) {
        String pattern = "^([a-zA-Z]+[\\s'.]?)+\\S$";
        Pattern p1 = Pattern.compile(pattern);
        Matcher m1 = p1.matcher(name);
        return m1.find();
    }

}

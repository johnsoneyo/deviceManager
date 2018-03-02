#!/bin/bash

mvn clean package
#nohup java -jar target/DeviceManager-0.0.1-SNAPSHOT.jar > device-manager.log 2>&1&
mvn spring-boot:run

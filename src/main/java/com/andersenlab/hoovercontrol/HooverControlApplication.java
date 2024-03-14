package com.andersenlab.hoovercontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HooverControlApplication {
    //TODO using HTTPS with TLS on self-signed cert
        //https://www.baeldung.com/spring-boot-configure-tomcat
        //https://www.baeldung.com/spring-boot-https-self-signed-certificate

    public static void main(String[] args) {
        SpringApplication.run(HooverControlApplication.class, args);
    }

}

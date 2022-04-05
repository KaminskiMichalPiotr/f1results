package com.f1.f1results;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class F1resultApplication {

    public static void main(String[] args) {
        SpringApplication.run(F1resultApplication.class, args);
    }

}

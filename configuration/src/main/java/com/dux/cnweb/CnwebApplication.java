package com.dux.cnweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dux.cnweb", "com.dux.cnweb.shared"})
public class CnwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnwebApplication.class, args);
    }
}
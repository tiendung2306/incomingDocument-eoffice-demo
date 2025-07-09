package com.dux.cnweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dux.cnweb")
public class CnwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnwebApplication.class, args);
    }
}
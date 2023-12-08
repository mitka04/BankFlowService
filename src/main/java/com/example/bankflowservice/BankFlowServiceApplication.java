package com.example.bankflowservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.bankflowservice.model")
public class BankFlowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankFlowServiceApplication.class, args);
    }

}

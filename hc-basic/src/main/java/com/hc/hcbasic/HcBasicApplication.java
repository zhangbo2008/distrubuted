package com.hc.hcbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HcBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcBasicApplication.class, args);
    }

}

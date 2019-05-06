package com.hc.hcsso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HcSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcSsoApplication.class, args);
    }

}

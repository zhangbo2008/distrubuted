package com.hc.hcserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class HcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcServerApplication.class, args);
    }

}

package com.knuipalab.dsmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DsmpApplication {
    @RequestMapping("/")
    public String home() {
        return "Hello Spring World!!";
    }
    public static void main(String[] args) {
        SpringApplication.run(DsmpApplication.class, args);
    }

}

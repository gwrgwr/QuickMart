package com.example.quickmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QuickMartApplication {


    public static void main(String[] args) {
        SpringApplication.run(QuickMartApplication.class, args);
    }

}

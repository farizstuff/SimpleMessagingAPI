package com.fariz.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {

    static { System.setProperty("logging.config", "logback-spring.xml");}

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
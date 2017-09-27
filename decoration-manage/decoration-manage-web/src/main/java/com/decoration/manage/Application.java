package com.decoration.manage;

import java.util.List;

import org.springframework.boot.SpringApplication;

import com.decoration.manage.config.DecorationConfig;


public class Application {

    public static void main(String[] args){
        SpringApplication.run(DecorationConfig.class, args);
    }
}


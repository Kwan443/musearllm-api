package com.musearllm.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.musearllm.api.mapper")
public class MuseARLLMApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuseARLLMApplication.class, args);
    }
}
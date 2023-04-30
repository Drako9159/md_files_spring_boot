package com.website.blog.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//@SpringBootApplication
public class SpringBootResourcesApplication implements ApplicationRunner {
    @Value("classpath:/posts/static/articles") // Do not use field injection
    private Resource resource;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootResourcesApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (InputStream inputStream = resource.getInputStream()) {
            String string = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(string);
        }
    }
}

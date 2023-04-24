package com.website.blog.controller;

import com.website.blog.utils.MdFileReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/test")
public class TestController {

    public Set<String> listFile(String dir){
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }


    @GetMapping
    public ResponseEntity test() throws IOException {
        Set<String> checks = listFile("src/main/posts");
        List<String> filenames = new ArrayList<>(checks);/*
        try (
                InputStream in = getResourceAsStream("/posts/");
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
            System.out.println(filenames);
        }*/

        return ResponseEntity.ok(filenames);
    }

    private static InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? MdFileReader.class.getResourceAsStream(resource) : in;
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}

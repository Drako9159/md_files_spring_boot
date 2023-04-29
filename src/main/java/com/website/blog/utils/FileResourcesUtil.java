package com.website.blog.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileResourcesUtil {
    public static void main(String[] args) throws IOException {

        FileResourcesUtil app = new FileResourcesUtil();

        // read all files from a resources folder
        try {

            // files from src/main/resources/json
            List<File> result = app.getAllFilesFromResource("posts/static/articles");/*
            for (File file : result) {
                System.out.println("file : " + file);
                printFile(file);
            }*/
/*
            InputStream inputStream = new ClassPathResource("/posts/static/articles/")
                    .getInputStream();
            System.out.println(inputStream);
            Set<String> views = app.reader("src/main/posts/static/articles");
            System.out.println(views);*/
            //System.out.println(result);

            //System.out.println(app.readPosts());

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

    public static Set<String> reader(String dir) throws IOException {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static List<String> readPosts() throws IOException {
        List<String> filenames = new ArrayList<>();
        try (
                InputStream in = getResourceAsStream("/posts/static/articles");
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
            return filenames;
        }
    }
    private static InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? MdFileReader.class.getResourceAsStream(resource) : in;
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    private List<File> getAllFilesFromResource(String folder)
            throws URISyntaxException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(folder);
        // dun walk the root path, we will walk all the classes
        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                .filter(Files::isRegularFile)
                .map(x -> x.toFile())
                .collect(Collectors.toList());

        return collect;
    }

    // print a file
    private static void printFile(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

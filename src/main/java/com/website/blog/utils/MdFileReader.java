package com.website.blog.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MdFileReader {
    public static List<String> readLinesFromMdFile(String filename) {
        try {
            InputStream inputStream = new ClassPathResource("/posts/static/articles/" + filename)
                    .getInputStream();
            //InputStream inputStream = new FileInputStream("src/main/posts/static/articles/" + filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            return bufferedReader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> readPosts() throws IOException {
        List<String> filenames = new ArrayList<>();
        try (
                InputStream in = getResourceAsStream("/posts/");
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

    public static String getTitleFromFileName(String filename) {
        String fileNameBeforeExtension = filename.split(".md")[0];
        String[] tokens = fileNameBeforeExtension.split("_");

        String[] titleTokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        return String.join(" ", titleTokens);
    }

    public static long getIdFromFileName(String filename) {
        String fileNameBeforeExtension = filename.split(".md")[0];
        return Long.parseLong(fileNameBeforeExtension.split("_")[0]);

    }


    public static Set<String> reader(String dir) throws IOException {



        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

}

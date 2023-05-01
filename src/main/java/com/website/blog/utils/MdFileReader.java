package com.website.blog.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MdFileReader {
    public static void main(String[] args) throws URISyntaxException, IOException {
        MdFileReader mdFileReader = new MdFileReader();
        mdFileReader.test();
    }

    public static List<String> readLinesFromMdFile(String filename) {
        try {


            InputStream inputStream = new ClassPathResource("/posts/md_files/" + filename)
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

    public List<String> readPosts() throws IOException, URISyntaxException {
        List<String> filenames = new ArrayList<>();
        test();
        try (
                InputStream in = getResourceAsStream("/posts/md_files");
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            // test
            //List<File> result = getAllFilesFromResource("posts/md_files");
            //List files = result.stream().filter(e -> !e.isDirectory()).map((File::getName)).collect(Collectors.toList());



            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
            return filenames;
        }
    }
    public void test() throws URISyntaxException, IOException {
        /*
        String path = "posts/md_files";
        Set<String> tester = Stream.of(Objects.requireNonNull(new File(path).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
        System.out.println(tester);*/

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("posts/md_files");
        // dun walk the root path, we will walk all the classes
        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                .filter(Files::isRegularFile)
                .map(x -> x.toFile())
                .collect(Collectors.toList());
        //System.out.println(collect);
        System.out.println(collect.size());
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
        String path = "posts/md_files";
        return Stream.of(Objects.requireNonNull(new File(path).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

}

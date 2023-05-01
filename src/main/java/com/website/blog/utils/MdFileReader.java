package com.website.blog.utils;

import com.website.blog.models.DataListArticles;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MdFileReader {
    public static void main(String[] args) throws URISyntaxException, IOException {
        MdFileReader mdFileReader = new MdFileReader();
        System.out.println(mdFileReader.listArticles());

    }

    public List<DataListArticles> listArticles() throws URISyntaxException, IOException {
        return getFilenames().stream().map((e) -> readHeaders(e)).collect(Collectors.toList());
    }

    public DataListArticles readHeaders(String file) {
        List<String> headers = readLinesFromMdFile(file);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = sdf.parse(headers.get(10).split(":")[1]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new DataListArticles(headers.get(1).split(":")[1], headers.get(2).split(":")[1],
                headers.get(3).split(":")[1], headers.get(4).split(":")[1],
                headers.get(5).split(":")[1], headers.get(6).split(":")[1],
                headers.get(7).split(":")[1], headers.get(8).split(":")[1],
                headers.get(9).split(":")[1], headers.get(10).split(":")[1],
                headers.get(11).split(":")[1], headers.get(12).split(":")[1],
                date);
    }


    public List<String> getFilenames() throws URISyntaxException, IOException {
        return readerMdFiles().stream()
                .filter((e) -> !e.isDirectory())
                .map(e -> e.getName())
                .collect(Collectors.toList());
    }

    public List<File> readerMdFiles() throws URISyntaxException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("posts/md_files");
        // dun walk the root path, we will walk all the classes
        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                .filter(Files::isRegularFile)
                .map(x -> x.toFile())
                .collect(Collectors.toList());
        return collect;
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

    public String readContentFileMd(String id) {
        try {
            List<String> elementsMd = readLinesFromMdFile(checkIdFilename(id));
            assert elementsMd != null;
            elementsMd.subList(0, 14).clear();
            return MdToHtmlRenderer.renderHtml(elementsMd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String checkIdFilename(String id) throws URISyntaxException, IOException {
        AtomicReference<String> result = new AtomicReference<>("");
        getFilenames().forEach((e) -> {
            Pattern pattern = Pattern.compile("(\\d+_)");
            new ArrayList<>().add(e.split(pattern.split(e)[1])[0].split("_")[0]);
            if (e.contains(id)) {
                result.set(e);
            }
        });
        return result.toString();
    }

    public List<String> readPosts() throws IOException, URISyntaxException {
        List<String> filenames = new ArrayList<>();
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

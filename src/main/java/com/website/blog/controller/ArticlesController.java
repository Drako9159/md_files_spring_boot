package com.website.blog.controller;

import com.website.blog.models.DataListArticles;
import com.website.blog.utils.MdFileReader;
import com.website.blog.utils.MdToHtmlRenderer;
import com.website.blog.utils.SorterElements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @GetMapping
    public ResponseEntity<Page<DataListArticles>> listArticles(Pageable pageable) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        MdFileReader mdFileReader = new MdFileReader();
        List<DataListArticles> articlesList = new ArrayList<>();


        mdFileReader.reader("src/main/posts/static/articles").forEach(e -> {
            List<String> header = mdFileReader.readLinesFromMdFile(e);
            Date date;
            try {
                date = sdf.parse(header.get(10).split(":")[1]);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            articlesList.add(new DataListArticles(header.get(1).split(":")[1], header.get(2).split(":")[1],
                    header.get(3).split(":")[1], header.get(4).split(":")[1],
                    header.get(5).split(":")[1], header.get(6).split(":")[1],
                    header.get(7).split(":")[1], header.get(8).split(":")[1],
                    header.get(9).split(":")[1], header.get(10).split(":")[1],
                    header.get(11).split(":")[1], header.get(12).split(":")[1],
                    date));
        });

        SorterElements sorterElements = new SorterElements();
        articlesList.sort((e1, e2) -> e1.id().compareTo(e2.id()));

        String[] strShortAvailable = {"id", "category", "tag", "filename", "language", "title", "date", "readTime", "author"};
        List<String> mylist = Arrays.asList(strShortAvailable);
        if (!pageable.getSort().toString().equals("UNSORTED")) {
            if (mylist.contains(pageable.getSort().toString().split(":")[0])) {
                sorterElements.sortBy(
                        articlesList,
                        pageable.getSort().toString().split(":")[0],
                        pageable.getSort().toString().split(":")[1]
                );
            }
        }
        int pagesize = pageable.getPageSize() ; // page size default 20
        long pageOffset = pageable.getOffset();
        long total = pageOffset + articlesList.size() + (articlesList.size() == pagesize ? pagesize : 0);

        int totalPages = articlesList.size() / pagesize;
        int pageNos = pageable.getPageNumber();
        int max = pageNos >= totalPages ? articlesList.size() : pagesize * (pageNos + 1);
        int min = pageNos > totalPages ? max:pagesize * pageNos;

        Page<DataListArticles> page = new PageImpl<>(articlesList.subList(min, max), pageable, total);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity getArticle(@PathVariable String id) {
        try {
            MdToHtmlRenderer mdHTML = new MdToHtmlRenderer();
            MdFileReader mdFileReader = new MdFileReader();
            List<String> elementsMd = mdFileReader.readLinesFromMdFile(getElementById(id));
            elementsMd.subList(0, 14).clear();
            String htmlRenderer = mdHTML.renderHtml(elementsMd);
            return ResponseEntity.ok(htmlRenderer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("FILE_NOT_FOUND");
        }
    }

    public String getElementById(String id) throws IOException {
        MdFileReader mdFileReader = new MdFileReader();
        List<String> listElements = new ArrayList<>();
        Set<String> mdFiles = mdFileReader.reader("src/main/posts/static/articles");
        System.out.println(mdFiles);
        InputStream inputStream = new ClassPathResource("/posts/static/articles/")
                .getInputStream();
        System.out.println(inputStream);
        AtomicReference<String> result = new AtomicReference<>("");
        mdFiles.forEach((e) -> {
            Pattern pattern = Pattern.compile("(\\d+_)");
            listElements.add(e.split(pattern.split(e)[1])[0].split("_")[0]);
            if (e.contains(id)) {
                result.set(e);
            }
        });
        //System.out.println(listElements);
        return result.toString();
    }
}

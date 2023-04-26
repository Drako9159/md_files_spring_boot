package com.website.blog.controller;

import com.website.blog.models.DataListArticles;
import com.website.blog.utils.MdFileReader;
import com.website.blog.utils.MdToHtmlRenderer;
import com.website.blog.utils.SorterElements;
import lombok.Data;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @GetMapping
    public ResponseEntity<Page<DataListArticles>> listArticles(Pageable pageable) {
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
        //Page<DataListArticles> page = new PageImpl<>(articlesList);
        //Page<DataListArticles> pages = new PageImpl<>(articlesList);
        //PagedListHolder page2 = new PagedListHolder(articlesList)
        // ;
        List<DataListArticles> articlesQuery = new ArrayList<>();


        int pageSize = pageable.getPageSize();
        long pageOffset = pageable.getOffset();
        Sort shortQuery = pageable.getSort();
        int totalPages = articlesList.size() / pageSize;

        //PageRequest pageable2 = new PageRequest(pageable.getPageNumber(), pageSize);
        //int max = pageable.getPageNumber() > totalPages ? articlesList.size() : pageSize * (pageable.getPageNumber() + 1);
        //int min = pageable.getPageNumber() > totalPages ? max : pageSize * pageable.getPageNumber();

        // SORT BY ID ASC
        /*
        Collections.sort(articlesList, new Comparator<DataListArticles>() {
            @Override
            public int compare(DataListArticles o1, DataListArticles o2) {
                return o1.id().compareTo(o2.id());
            }
        });*/
        // SORT BY ID ASC
        //Collections.sort(articlesList, Comparator.comparing(DataListArticles::id));
        // SORT BY ID DSC
        //Collections.sort(articlesList, (e1, e2) -> e2.id().compareTo(e1.id()));
        // DEFAULT SORT BY ID ASC
        Collections.sort(articlesList, (e1, e2) -> e1.id().compareTo(e2.id()));
        //Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        SorterElements sorterElements = new SorterElements();

        String[] strShortAvailable = {"id", "category", "tag", "filename", "language", "color", "title", "date", "readTime", "author"};
        List<String> mylist = Arrays.asList(strShortAvailable);
        if (!pageable.getSort().toString().equals("UNSORTED")) {
            if (mylist.contains(pageable.getSort().toString().split(":")[0])) {
                System.out.println(pageable.getSort().get());
                sorterElements.sortBy(
                        articlesList,
                        pageable.getSort().toString().split(":")[0]
                        );
            }
        }

        //?page=1&size=20&sort=id,asc
        long total = pageOffset + articlesList.size() + (articlesList.size() == pageSize ? pageSize : 0);
        //articlesQuery = articlesList.subList(0, articlesList.size());
        Page<DataListArticles> page = new PageImpl<>(articlesList, pageable, total);

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

    public String getElementById(String id) {
        MdFileReader mdFileReader = new MdFileReader();
        List<String> listElements = new ArrayList<>();
        Set<String> mdFiles = mdFileReader.reader("src/main/posts/static/articles");
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

package com.website.blog.controller;

import com.website.blog.models.DataListArticles;
import com.website.blog.utils.MdFileReader;
import com.website.blog.utils.MdToHtmlRenderer;
import com.website.blog.utils.PageableConfig;
import com.website.blog.utils.SorterElements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @GetMapping
    public ResponseEntity<Page<DataListArticles>> listArticles(Pageable pageable) throws IOException, URISyntaxException {
        MdFileReader mdFileReader = new MdFileReader();
        List<DataListArticles> articlesList = mdFileReader.listArticles();
        // DEFAULT SORT BY ID
        articlesList.sort((e1, e2) -> e1.id().compareTo(e2.id()));
        articlesList = new SorterElements().sortBy(pageable, articlesList);

        return ResponseEntity.ok(new PageableConfig().PageableConfig(pageable, articlesList));
    }

    @GetMapping("/{id}")
    public ResponseEntity getArticle(@PathVariable String id) {
        MdFileReader mdFileReader = new MdFileReader();
        try {
            return ResponseEntity.ok(mdFileReader.readContentFileMd(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("FILE_NOT_FOUND");
        }
    }

}

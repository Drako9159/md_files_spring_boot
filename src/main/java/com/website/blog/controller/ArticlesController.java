package com.website.blog.controller;

import com.website.blog.domain.article.Article;
import com.website.blog.domain.article.ArticlesRepository;
import com.website.blog.models.DataListArticles;
import com.website.blog.utils.MdFileReader;
import com.website.blog.utils.MdToHtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @GetMapping
    public ResponseEntity<Page<DataListArticles>> listArticles(@PageableDefault(size = 5) Pageable pagination){
        MdFileReader mdFileReader = new MdFileReader();
        List<DataListArticles> articlesList = new ArrayList<>();
        mdFileReader.reader("src/main/posts/static/articles").forEach(e -> {
            List<String> header = mdFileReader.readLinesFromMdFile(e);
            articlesList.add(new DataListArticles(header.get(1).split(":")[1], header.get(2).split(":")[1],
                    header.get(3).split(":")[1], header.get(4).split(":")[1],
                    header.get(5).split(":")[1], header.get(6).split(":")[1],
                    header.get(7).split(":")[1], header.get(8).split(":")[1],
                    header.get(9).split(":")[1], header.get(10).split(":")[1],
                    header.get(11).split(":")[1], header.get(12).split(":")[1]));
        });
        Page<DataListArticles> page = new PageImpl<>(articlesList);
        //Page<DataListArticles> pages = new PageImpl<>(articlesList);


        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getArticle(@PathVariable String id)  {
        try{
            MdToHtmlRenderer mdHTML = new MdToHtmlRenderer();
            MdFileReader mdFileReader = new MdFileReader();
            List<String> elementsMd = mdFileReader.readLinesFromMdFile(getElementById(id));
            elementsMd.subList(0, 14).clear();
            String htmlRenderer = mdHTML.renderHtml(elementsMd);
            return ResponseEntity.ok(htmlRenderer);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("FILE_NOT_FOUND");
        }
    }

    public String getElementById(String id){
        MdFileReader mdFileReader = new MdFileReader();
        List<String> listElements = new ArrayList<>();
        Set<String> mdFiles = mdFileReader.reader("src/main/posts/static/articles");
        AtomicReference<String> result = new AtomicReference<>("");
        mdFiles.forEach((e) -> {
            Pattern pattern = Pattern.compile("(\\d+_)");
            listElements.add(e.split(pattern.split(e)[1])[0].split("_")[0]);
            if(e.contains(id)){
                result.set(e);
            }
        });
        //System.out.println(listElements);
        return result.toString();
    }
}

package com.website.blog.utils;

import com.website.blog.domain.article.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.Optional;

public class ContextEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("classpath:posts/*")
    private Resource[] articlesFiles;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Arrays.stream(articlesFiles).forEach(e -> {
            Optional<String> postFileNameOpt = Optional.ofNullable(e.getFilename());
            Article article = new Article();
        });
    }
}

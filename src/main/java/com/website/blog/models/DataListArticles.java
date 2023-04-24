package com.website.blog.models;

import com.website.blog.domain.article.Article;

public record DataListArticles(String id, String category,
                               String tag, String filename,
                               String language, String color,
                               String title, String image,
                               String description, String createdAt,
                               String readTime, String author) {
/*
    public DataListArticles(Article article){
        this(article.getId(), article.getCategory(),
                article.getTag(), article.getFilename(),
                article.getLanguage(), article.getColor(),
                article.getTitle(), article.getImage(),
                article.getDescription(), article.getCreatedAt(),
                article.getReadTime(), article.getAuthor());
    }*/

}

package com.website.blog.domain.article;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String id;
    private String category;
    private String tag;
    private String filename;
    private String language;
    private String color;
    private String title;
    private String image;
    private String description;
    private String createdAt;
    private String readTime;
    private String author;

}

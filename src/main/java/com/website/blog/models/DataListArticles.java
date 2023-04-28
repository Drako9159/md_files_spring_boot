package com.website.blog.models;

import java.util.Date;

public record DataListArticles(String id, String category,
                               String tag, String filename,
                               String language, String color,
                               String title, String image,
                               String description, String createdAt,
                               String readTime, String author, Date date) {
}

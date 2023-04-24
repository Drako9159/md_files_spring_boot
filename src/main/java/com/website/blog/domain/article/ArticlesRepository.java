package com.website.blog.domain.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticlesRepository{
    Article article = new Article();
    Page<Article> finder(Pageable pagination);
}

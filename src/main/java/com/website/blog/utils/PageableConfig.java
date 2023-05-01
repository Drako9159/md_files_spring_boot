package com.website.blog.utils;

import com.website.blog.models.DataListArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageableConfig {

    public Page<DataListArticles> PageableConfig(Pageable pageable, List<DataListArticles> articlesList){
        int pagesize = pageable.getPageSize() ; // page size default 20
        long pageOffset = pageable.getOffset();
        long total = pageOffset + articlesList.size() + (articlesList.size() == pagesize ? pagesize : 0);

        int totalPages = articlesList.size() / pagesize;
        int pageNos = pageable.getPageNumber();
        int max = pageNos >= totalPages ? articlesList.size() : pagesize * (pageNos + 1);
        int min = pageNos > totalPages ? max:pagesize * pageNos;

        return new PageImpl<>(articlesList.subList(min, max), pageable, total);
    }
}

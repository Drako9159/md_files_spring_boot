package com.website.blog.utils;

import com.website.blog.models.DataListArticles;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SorterElements {

    public List<DataListArticles> sortBy(List articlesList, String sortBy){
        Collections.sort(articlesList, Comparator.comparing(DataListArticles::id));
        switch (sortBy) {
            case ("id"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::id));
                break;
            case ("category"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::category));
                break;
            case ("tag"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::tag));
                break;
            case ("filename"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::filename));
                break;
            case ("color"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::color));
                break;
            case ("title"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::title));
                break;
            case ("date"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::date));
                break;
            case ("readTime"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::readTime));
                break;
            case ("author"):
                Collections.sort(articlesList, Comparator.comparing(DataListArticles::author));
                break;
        }
        return articlesList;
    }
}

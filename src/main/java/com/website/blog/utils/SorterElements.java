package com.website.blog.utils;

import com.website.blog.models.DataListArticles;

import java.util.List;

public class SorterElements {

    public List<DataListArticles> sortBy(List<DataListArticles> articlesList, String sortBy, String order) {

        boolean sortConditionDESC = order.contains("DESC");

        switch (sortBy) {
            case ("id") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.id().compareTo(e1.id()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.id().compareTo(e2.id()));
            }
            case ("category") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.category().compareTo(e1.category()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.category().compareTo(e2.category()));
            }
            case ("tag") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.tag().compareTo(e1.tag()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.tag().compareTo(e2.tag()));
            }
            case ("filename") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.filename().compareTo(e1.filename()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.filename().compareTo(e2.filename()));
            }
            case ("title") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.title().compareTo(e1.title()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.title().compareTo(e2.title()));
            }
            case ("date") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.date().compareTo(e1.date()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.date().compareTo(e2.date()));
            }
            case ("readTime") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.readTime().compareTo(e1.readTime()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.readTime().compareTo(e2.readTime()));
            }
            case ("author") -> {
                if (sortConditionDESC) {
                    articlesList.sort((e1, e2) -> e2.author().compareTo(e1.author()));
                    break;
                }
                articlesList.sort((e1, e2) -> e1.author().compareTo(e2.author()));
            }
        }

        return articlesList;
    }
}

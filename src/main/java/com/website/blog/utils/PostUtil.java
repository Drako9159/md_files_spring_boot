package com.website.blog.utils;

import org.jsoup.Jsoup;

import java.util.List;
import java.util.Optional;

import static com.website.blog.utils.MdToHtmlRenderer.renderHtml;

public class PostUtil {
    public static String getHtmlContentFromMdLines(List<String> mdLines) {
        Optional<List<String>> mdLinesOpt = Optional.ofNullable(mdLines);
        return mdLinesOpt.isEmpty() ? "" : renderHtml(mdLinesOpt.get());
    }
    public static String getSynopsisFromHtmlContent(String htmlContent){
        String content = Jsoup.parse(htmlContent).text();
        return content.length() <= 150 ? content : content.substring(0, 149);
    }
}

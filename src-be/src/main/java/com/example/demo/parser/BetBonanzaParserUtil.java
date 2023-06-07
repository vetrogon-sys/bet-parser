package com.example.demo.parser;

import com.example.demo.entity.SportType;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BetBonanzaParserUtil {

    @SneakyThrows
    public static Elements getRootElementsByUri(String uri) {
        Document document = Jsoup.connect(uri)
              .userAgent("Mozilla")
              .timeout(5000)
              .referrer("http://google.com")
              .get();

        return document.select(".menu");
    }

    public static Elements getSportElementsByUri(String uri) {

        return getRootElementsByUri(uri);
    }

    @SneakyThrows
    public static SportType getSportTypeFromElements(Elements elements) {
        String type = elements.parents().select(".title").text();
        return new SportType(type, Collections.emptyList());
    }
}

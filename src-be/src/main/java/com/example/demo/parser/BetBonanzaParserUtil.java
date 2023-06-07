package com.example.demo.parser;

import com.example.demo.entity.SportType;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

    @SneakyThrows
    public static Elements getStatisticsElements(String uri) {
        Document document = Jsoup.connect(uri)
              .userAgent("Mozilla")
              .timeout(5000)
              .referrer("http://google.com")
              .get();

        return document.select(".football").select("table.highlights").select("a");
    }

}

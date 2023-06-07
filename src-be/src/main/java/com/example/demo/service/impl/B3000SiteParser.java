package com.example.demo.service.impl;

import com.example.demo.entity.SportType;
import com.example.demo.entity.Statistic;
import com.example.demo.parser.BetBonanzaParserUtil;
import com.example.demo.service.SiteParser;
import com.example.demo.service.SportTypeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class B3000SiteParser implements SiteParser {
    @Value("${bookmaker.site.b3000.url}")
    private String siteUrl;

    private final RestTemplate restTemplate;
    private final SportTypeService sportTypeService;

    @Override
    @SneakyThrows
    public List<Statistic> fetchAndParseStatistic() {
        Elements availableMenus = BetBonanzaParserUtil.getRootElementsByUri(siteUrl);

        for (Element element : availableMenus.toArray(new Element[0])) {
            if (!element.children().get(0).children().attr("href").isEmpty()
                  || element.children().text().isEmpty()) {
                //scip special offers
                continue;
            }
            String sportUri = element.children().get(0)
                  .children().select("a")
                  .get(0).attr("href");

            Elements sportElements = BetBonanzaParserUtil.getSportElementsByUri(sportUri);

            SportType currrentSportType
                  = sportTypeService.saveIfNotExist(BetBonanzaParserUtil.getSportTypeFromElements(sportElements));



        }


        return null;
    }


}

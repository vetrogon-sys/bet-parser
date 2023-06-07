package com.example.demo.service.impl;

import com.example.demo.entity.Statistic;
import com.example.demo.service.SiteParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class B3000SiteParser implements SiteParser {

    private final RestTemplate restTemplate;

    @Value("bookmaker.site.b3000.url")
    private String siteUrl;

    @Override
    public List<Statistic> fetchAndParseStatistic() {
        return null;
    }
}

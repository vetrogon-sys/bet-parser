package com.example.demo.service;

import com.example.demo.entity.Statistic;

import java.util.List;

public interface SiteParser {

    List<Statistic> fetchAndParseStatistic();

}

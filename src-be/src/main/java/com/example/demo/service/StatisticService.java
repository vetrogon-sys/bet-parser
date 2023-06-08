package com.example.demo.service;

import com.example.demo.dto.StatisticDto;
import com.example.demo.entity.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatisticService {

    Page<StatisticDto> getAll(Pageable pageable);

    List<StatisticDto> saveAll(List<Statistic> statistics);

    void deleteAll();

    String getStatisticsAsHtml();
}

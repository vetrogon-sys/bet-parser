package com.example.demo.controller;

import com.example.demo.dto.StatisticDto;
import com.example.demo.entity.Statistic;
import com.example.demo.service.SiteParser;
import com.example.demo.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;
    private final SiteParser siteParser;

    @GetMapping
    public ResponseEntity<List<StatisticDto>> getStatistic(Pageable pageable) {
        List<StatisticDto> statisticDtos = statisticService.getAll(pageable);
        if (statisticDtos.size() > 0) {
            return ResponseEntity.ok(statisticDtos);
        }

        statisticService.saveAll(siteParser.fetchAndParseStatistic());
        return ResponseEntity.ok(statisticService.getAll(pageable));
    }

}

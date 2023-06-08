package com.example.demo.controller;

import com.example.demo.dto.StatisticDto;
import com.example.demo.service.SiteParser;
import com.example.demo.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;
    private final SiteParser siteParser;

    @GetMapping
    public ResponseEntity<Page<StatisticDto>> getStatistic(Pageable pageable) {
        return ResponseEntity.ok(statisticService.getAll(pageable));
    }

    @PatchMapping
    public ResponseEntity<Void> refreshStatistics() {
        statisticService.deleteAll();

        statisticService.saveAll(siteParser.fetchAndParseStatistic());

        return ResponseEntity.ok()
              .build();
    }


    @SneakyThrows
    @GetMapping("/download/html")
    public ResponseEntity<Resource> downloadStatisticAsHtml() {
        String statisticsAsHtml = statisticService.getStatisticsAsHtml();
        return ResponseEntity.ok()
              .contentType(MediaType.TEXT_HTML)
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"statistics_" + LocalDateTime.now() + ".html\"")
              .body(new ByteArrayResource(statisticsAsHtml.getBytes()));
    }

}

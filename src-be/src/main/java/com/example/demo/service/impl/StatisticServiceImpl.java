package com.example.demo.service.impl;

import com.example.demo.dto.StatisticDto;
import com.example.demo.entity.Statistic;
import com.example.demo.mapper.StatisticMapper;
import com.example.demo.parser.HtmlTableParserUtil;
import com.example.demo.repository.StatisticRepository;
import com.example.demo.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;

    @Override
    public List<StatisticDto> getAll(Pageable pageable) {
        return statisticRepository.findAll(pageable)
              .stream()
              .map(statisticMapper::mapToDto)
              .toList();
    }

    @Override
    public List<StatisticDto> saveAll(List<Statistic> statistics) {
        return statisticRepository.saveAll(statistics)
              .stream()
              .map(statisticMapper::mapToDto)
              .toList();
    }

    @Override
    @SneakyThrows
    public String getStatisticsAsHtml() {
        List<StatisticDto> statisticDtos = statisticRepository.findAll().stream()
              .map(statisticMapper::mapToDto)
              .toList();
        return HtmlTableParserUtil.getHtmlTableForStatistics(statisticDtos);
    }

}

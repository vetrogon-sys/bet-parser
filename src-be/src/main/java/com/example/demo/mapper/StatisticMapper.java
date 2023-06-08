package com.example.demo.mapper;

import com.example.demo.dto.StatisticDto;
import com.example.demo.entity.Statistic;
import org.springframework.stereotype.Component;

@Component
public class StatisticMapper {

    public StatisticDto mapToDto(Statistic statistic) {
        return StatisticDto.builder()
              .id(statistic.getId())
              .dateStart(statistic.getDateStart())
              .tournament(statistic.getTournament().getName())
              .link(statistic.getLink())
              .sportType(statistic.getSportType().getType())
              .teams(statistic.getTeams())
              .build();
    }

}

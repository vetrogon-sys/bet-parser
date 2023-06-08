package com.example.demo.dto;

import com.example.demo.parser.util.HtmlParserUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {

    private Long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateStart;

    private String tournament;

    private String teams;

    private String sportType;

    private String link;

    public String getAsHtmlRow() {
        String htmlRow = HtmlParserUtil.getAsCell(String.valueOf(id)) +
              HtmlParserUtil.getAsCell(tournament) +
              HtmlParserUtil.getAsCell(teams) +
              HtmlParserUtil.getAsCell(dateStart.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) +
              HtmlParserUtil.getAsCell(sportType) +
              HtmlParserUtil.getAsCellWithHref(link);

        return HtmlParserUtil.getAsRow(htmlRow);
    }

}

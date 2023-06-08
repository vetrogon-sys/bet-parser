package com.example.demo.parser;

import com.example.demo.dto.StatisticDto;
import com.example.demo.parser.util.HtmlTemplateFileUtil;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlTableParserUtil {

    public static final String REPLACE_FLAG = "<br id=\"replace_with_the_content\" />";

    private HtmlTableParserUtil() {
    }

    public static String getHtmlTableForStatistics(List<StatisticDto> statistics) {
        String html = HtmlTemplateFileUtil.getHtmlTemplate();

        return html.replace(REPLACE_FLAG, getStatisticsAsHtmlRows(statistics));
    }

    private static String getStatisticsAsHtmlRows(List<StatisticDto> statistics) {
        return statistics.stream()
              .map(StatisticDto::getAsHtmlRow)
              .collect(Collectors.joining());
    }

}

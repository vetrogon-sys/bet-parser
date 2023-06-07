package com.example.demo.service.impl;

import com.example.demo.entity.SportType;
import com.example.demo.entity.Statistic;
import com.example.demo.entity.Tournament;
import com.example.demo.parser.BetBonanzaParserUtil;
import com.example.demo.service.SiteParser;
import com.example.demo.service.SportTypeService;
import com.example.demo.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BetbonanzaSiteParser implements SiteParser {
    @Value("${bookmaker.site.betbonanza.url}")
    private String siteUrl;

    private final SportTypeService sportTypeService;
    private final TournamentService tournamentService;

    @Override
    @SneakyThrows
    public List<Statistic> fetchAndParseStatistic() {
        Elements availableMenus = BetBonanzaParserUtil.getRootElementsByUri(siteUrl);
        List<Statistic> statistics = new ArrayList<>();

        log.info("start fetching tournaments...");
        availableMenus.parallelStream()
              .filter(BetbonanzaSiteParser::isMenuValid)
              .map(BetbonanzaSiteParser::getMenuRequiredAttrib)
              .map(BetBonanzaParserUtil::getRootElementsByUri)
              .forEach(sportElements -> {
                  Elements tournamentsElements = getTournamentsElements(sportElements);
                  SportType currrentSportType
                        = sportTypeService.saveIfNotExist(getSportTypeFromElements(sportElements));
                  log.debug("Tournaments for :" + currrentSportType.getType());
                  tournamentsElements.parallelStream()
                        .map(tournamentsElement -> getStatisticFromTournament(tournamentsElement, currrentSportType))
                        .forEach(statistics::addAll);
              });

        return statistics;
    }

    public SportType getSportTypeFromElements(Elements elements) {
        String type = elements.parents().select(".title").text();
        return SportType.builder()
              .type(type)
              .build();
    }

    private static String getMenuRequiredAttrib(Element availableMenu) {
        return availableMenu.children().get(0)
              .children().select("a")
              .get(0).attr("href");
    }

    private static boolean isMenuValid(Element availableMenu) {
        return availableMenu.children().get(0).children().attr("href").isEmpty()
              && !availableMenu.children().text().isEmpty();
    }

    private List<Statistic> getStatisticFromTournament(Element tournamentsElement, SportType sportType) {
        String link = getHrefFromElement(tournamentsElement.children().get(0));
        Elements statisticsElements = BetBonanzaParserUtil.getStatisticsElements(link);

        Tournament currTournament = Tournament.builder()
              .name(statisticsElements.get(0).parents().select(".page-header-row").text())
              .sportType(sportType)
              .build();

        final Tournament savedTournament = tournamentService.saveIfNotExist(currTournament);

        return statisticsElements.stream()
              .map(statisticsElement -> buildStatistic(sportType, savedTournament, statisticsElement))
              .toList();
    }

    private static Statistic buildStatistic(SportType sportType, Tournament currTournament, Element statisticsElement) {
        return Statistic.builder()
              .teams(statisticsElement.select(".clubs").select("p").text())
              .dateStart(getDateStart(statisticsElement))
              .link(getHrefFromElement(statisticsElement))
              .sportType(sportType)
              .tournament(currTournament)
              .build();
    }

    private static String getHrefFromElement(Element statisticsElement) {
        return statisticsElement.select("a").attr("href");
    }

    private static LocalDateTime getDateStart(Element statisticsElement) {
        String[] split = statisticsElement.select(".time")
              .select("td")
              .text().split(" ");

        String[] shortMonths = new DateFormatSymbols().getShortMonths();

        int monthNum = 0;
        for (int i = 0; i < shortMonths.length; i++) {
            if (shortMonths[i].equals(split[2])) {
                monthNum = i + 1;
                break;
            }
        }

        LocalDate curDate = LocalDate.of(LocalDate.now().getYear(), monthNum, Integer.parseInt(split[1]));
        String[] ti = split[3].split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(ti[0]), Integer.parseInt(ti[1]));
        return LocalDateTime.of(curDate, time);
    }

    private Elements getTournamentsElements(Elements elements) {
        Elements tournament = new Elements();
        for (Element element : elements) {
            String categoryUri = getMenuRequiredAttrib(element);

            tournament.addAll(BetBonanzaParserUtil.getRootElementsByUri(categoryUri));
        }
        return tournament;
    }


}

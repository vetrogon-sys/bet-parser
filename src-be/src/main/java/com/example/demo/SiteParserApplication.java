package com.example.demo;

import com.example.demo.service.SiteParser;
import com.example.demo.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RequiredArgsConstructor
public class SiteParserApplication {

	private final SiteParser siteParser;
	private final StatisticService statisticService;

//	@EventListener(classes = { ContextRefreshedEvent.class })
//	public void handleMultipleEvents() {
//		statisticService.saveAll(siteParser.fetchAndParseStatistic());
//	}

	public static void main(String[] args) {
		SpringApplication.run(SiteParserApplication.class, args);
	}

}

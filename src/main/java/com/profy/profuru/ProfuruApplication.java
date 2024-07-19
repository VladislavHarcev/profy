package com.profy.profuru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableConfigurationProperties(CalendarProperties.class)
public class ProfuruApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfuruApplication.class, args);
	}

}
// главный файл для запуска сервиса
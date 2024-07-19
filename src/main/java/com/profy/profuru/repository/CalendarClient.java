package com.profy.profuru.repository;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "calendarClient",  url = "${calendar.client.url}")
public interface CalendarClient {
    @GetMapping("${calendar.client.path}")
    String getCalendar();
}

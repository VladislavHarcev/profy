package com.profy.profuru.component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.profy.profuru.DTO.CalendarDTO;
import com.profy.profuru.repository.CalendarClient;
import com.profy.profuru.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
@RequiredArgsConstructor
public class CalendarBean {
    private final CalendarService calendarService;
    private final CalendarClient calendarClient;
    private final ObjectMapper objectMapper;

    @Transactional
    public CalendarDTO getCalendar(){
        return calendarService.getCalendar(java.time.Year.now().getValue());
    }

    @SneakyThrows
    @Transactional
    @Scheduled(cron = "${calendar.update.scheduler.cron}")
    public CalendarDTO updateCalendarByFeign() {
        String jsonResponse = calendarClient.getCalendar();
        CalendarDTO calendarDTO = objectMapper.readValue(jsonResponse, CalendarDTO.class);
        for (CalendarDTO.MonthDto month : calendarDTO.getMonths()) {
            String daysStr = String.join(",", month.getDays());
            String[] daysArrayStr = daysStr.split(",");
            month.setDays(daysArrayStr);
        }
        CalendarDTO oldCalendar = calendarService.getCalendar(java.time.Year.now().getValue());
        if (oldCalendar != null){
            BeanUtils.copyProperties(calendarDTO, oldCalendar, "id");
            return calendarService.save(oldCalendar);
        }
        return calendarService.save(calendarDTO);
    }

}

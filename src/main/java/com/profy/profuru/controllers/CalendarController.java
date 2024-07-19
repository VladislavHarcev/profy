package com.profy.profuru.controllers;

import com.profy.profuru.DTO.CalendarDTO;
import com.profy.profuru.component.CalendarBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarBean calendarBean;

    @GetMapping("/get")
    public ResponseEntity<CalendarDTO> getCalendarData() {
        CalendarDTO calendarDTO = calendarBean.getCalendar();
        return ResponseEntity.ok(calendarDTO);
    }

    @GetMapping("/update")
    public ResponseEntity<CalendarDTO> updateCalendarByFeign() {
        CalendarDTO calendarDTO = calendarBean.updateCalendarByFeign();
        return ResponseEntity.ok(calendarDTO);
    }

}

package com.profy.profuru.service;

import com.profy.profuru.DTO.CalendarDTO;
import com.profy.profuru.models.CalendarData;
import com.profy.profuru.repository.CalendarDataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarDataRepository calendarDataRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public CalendarDTO save(CalendarDTO calendarDTO){
        CalendarData newCalendar = calendarDataRepository.save(modelMapper.map(calendarDTO, CalendarData.class));
        return modelMapper.map(newCalendar, CalendarDTO.class);
    }
    @Transactional
    public CalendarDTO getCalendar(int currentYear){
        CalendarData latestCalendar = calendarDataRepository.findFirstByYearOrderByCreateDateDesc(currentYear);
        if (latestCalendar != null){
            CalendarDTO calendarDTO = modelMapper.map(latestCalendar, CalendarDTO.class);
            return calendarDTO;
        }
        return null;
    }

    public boolean checkDay() {
        int currentYear = LocalDate.now().getYear();
        String currentMonth = String.format("%s", LocalDate.now().getMonthValue());
        String currentDay = String.format("[%s]", LocalDate.now().getDayOfMonth());
        List<UUID> ids = calendarDataRepository.checkDay(currentMonth, currentDay, currentYear);
        return !ids.isEmpty();
    }


}

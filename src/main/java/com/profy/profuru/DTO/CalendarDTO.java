package com.profy.profuru.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarDTO {

    @Schema(name = "Уникальный идетификатор")
    private UUID id;
    @Schema(name = "Год")
    private int year;
    @Schema(name = "Месяц")
    private List<MonthDto> months;
    @Schema(name = "Переходы")
    private List<TransitionDto> transitions;
    @Schema(name = "Статистика")
    private StatisticDto statistic;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "Месяца")
    public static class MonthDto {
        @Schema(name = "Месяц")
        private int month;
        @Schema(name = "Число выходного дня")
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        private String[] days;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TransitionDto {
        private String from;
        private String to;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "Статистика")
    public static class StatisticDto {
        @Schema(name = "Рабочих дней")
        private int workdays;
        @Schema(name = "Выходных")
        private int holidays;
        @Schema(name = "Рабочих часов(40 часов в неделю)")
        private double hours40;
        @Schema(name = "Рабочих часов(36 часов в неделю)")
        private double hours36;
        @Schema(name = "Рабочих часов(24 часа в неделю)")
        private double hours24;
    }
}


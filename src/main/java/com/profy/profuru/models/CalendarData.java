package com.profy.profuru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.type.SqlTypes;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "calendar_data")
public class CalendarData extends BaseEntity {

    @Column(name = "calendar_year")
    private int year;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "months", columnDefinition = "jsonb")
    private List<Month> months;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "transitions", columnDefinition = "jsonb")
    private List<Transition> transitions;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "statistic", columnDefinition = "jsonb")
    private Statistic statistic;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Month {
        private int month;
        private String[] days;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Transition {
        private String from;
        private String to;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Statistic {
        private int workdays;
        private int holidays;
        private double hours40;
        private double hours36;
        private double hours24;
    }
}
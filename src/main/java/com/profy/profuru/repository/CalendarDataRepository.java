package com.profy.profuru.repository;

import com.profy.profuru.models.CalendarData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface CalendarDataRepository extends CrudRepository<CalendarData, UUID> {
    CalendarData findFirstByYearOrderByCreateDateDesc(int currentYear);
    @Query(value = "SELECT id " +
            "FROM calendar_data cd " +
            "CROSS JOIN LATERAL jsonb_array_elements(cd.months) o(obj) " +
            "WHERE o.obj ->> 'month' = :currentMonth " +
            "AND (o.obj ->> 'days')::jsonb @> cast(:currentDay as jsonb) " +
            "AND cd.calendar_year = :currentYear",
            nativeQuery = true)
    List<UUID> checkDay(@Param("currentMonth") String currentMonth,
                                    @Param("currentDay") String currentDay,
                                    @Param("currentYear") int currentYear);
}
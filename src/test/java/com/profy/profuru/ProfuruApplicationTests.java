package com.profy.profuru;

import com.profy.profuru.repository.CalendarDataRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class ProfuruApplicationTests {
	CalendarDataRepository calendarDataRepository;

	@Test
	void contextLoads() {
	}

}

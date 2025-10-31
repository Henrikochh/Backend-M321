package com.CarRental.Buchungssystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ActiveProfiles("test")
class BuchungssystemApplicationTests {

	@MockBean
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

}

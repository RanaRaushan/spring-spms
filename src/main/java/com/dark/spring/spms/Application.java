package com.dark.spring.spms;

import com.dark.spring.spms.dao.ParkingSlotDao;
import com.dark.spring.spms.entity.ParkingSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	@Autowired
	ParkingSlotDao parkingSlotDao;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner startup() {

		return args -> {
			List<ParkingSlot> parkingSlots = new ArrayList<>();
			String[] prefixes = {"A", "B", "C", "D"};
			for (String prefix : prefixes) {
				for (int i = 1; i <= 25; i++) {
					String slotName = prefix + i;
					if (parkingSlotDao.getParkingBySlotName(slotName).isEmpty()) {
						parkingSlots.add(ParkingSlot.builder().slotName(slotName).build());
					}
					// Add parkingSlot to database
				}
			}
			parkingSlotDao.saveAll(parkingSlots);
			System.out.println("Database initialized!");

		};
	}

}

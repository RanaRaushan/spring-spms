package com.dark.spring.spms.service;


import com.dark.spring.spms.data.ParkingData;
import com.dark.spring.spms.dto.BookingDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ParkingService {
    List<ParkingData> getAllParkingBy(Map<String, Object> queryParams);

    ParkingData getParkingByIdOrSlotName(Object slotId);

    ParkingData updateParking(BookingDTO bookingDTO);
}

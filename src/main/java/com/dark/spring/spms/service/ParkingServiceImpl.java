package com.dark.spring.spms.service;


import com.dark.spring.spms.dao.ParkingSlotDao;
import com.dark.spring.spms.data.ParkingData;
import com.dark.spring.spms.dto.BookingDTO;
import com.dark.spring.spms.entity.ParkingSlot;
import com.dark.spring.spms.exceptions.ParkingSlotNotFoundException;
import com.dark.spring.spms.utils.CurrentUserService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingSlotDao parkingSlotDao;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    UserService userService;

    @Override
    public List<ParkingData> getAllParkingBy(Map<String, Object> queryParams) {
        List<ParkingSlot> parkingSlots = parkingSlotDao.findAll(
                (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    queryParams.forEach((key, value) -> {
                        switch (key) {
                            case "slotName":
                                predicates.add(criteriaBuilder.equal(root.get("slotName"), value));
                                break;
                            case "available":
                                predicates.add(criteriaBuilder.equal(root.get("available"), value));
                                break;
                            case "occupiedBy":
                                predicates.add(criteriaBuilder.equal(root.get("occupiedBy"), value));
                                break;
                            case "vehicleNo":
                                predicates.add(criteriaBuilder.equal(root.get("isOccupied"), value));
                                break;
                            case "createdAt":
                                predicates.add(criteriaBuilder.equal(root.get("createdAt"), value));
                                break;
                            case "updatedAt":
                                predicates.add(criteriaBuilder.equal(root.get("updatedAt"), value));
                                break;
                        }
                    });
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }
        );
        return parkingSlots.stream()
                .map(ParkingSlot::toData)
                .toList();
    }

    @Override
    public ParkingData getParkingByIdOrSlotName(Object slotId) {
        Optional<ParkingSlot> parkingSlot;
        if (isInteger(slotId)) {
            parkingSlot = parkingSlotDao.findByIdOrSlotName(Integer.valueOf(slotId.toString()), "");
        } else {
            parkingSlot = parkingSlotDao.findByIdOrSlotName(-1, String.valueOf(slotId));
        }
        if(parkingSlot.isPresent()) {
            return parkingSlot.get().toData();
        } else {
            throw new ParkingSlotNotFoundException("Parking not found");
        }
    }

    @Override
    public ParkingData updateParking(BookingDTO bookingDTO) {
        Optional<ParkingSlot> parkingSlot;
        if (isInteger(bookingDTO.getSlot())) {
            parkingSlot = parkingSlotDao.findByIdOrSlotName(Integer.valueOf(bookingDTO.getSlot().toString()), "");
        } else {
            parkingSlot = parkingSlotDao.findByIdOrSlotName(-1, String.valueOf(bookingDTO.getSlot()));
        }
        if(parkingSlot.isPresent()) {
            parkingSlot.get().setOccupiedBy(userService.getUserModelByEmail(bookingDTO.getUser()));
            parkingSlot.get().setVehicleNo(bookingDTO.getVehicleNo());
            return parkingSlotDao.save(parkingSlot.get()).toData();
        } else {
            throw new ParkingSlotNotFoundException("Parking not found");
        }
    }

    @Override
    public ParkingData emptyParkingSlot(BookingDTO bookingDTO) {
        Optional<ParkingSlot> parkingSlot;
        if (isInteger(bookingDTO.getSlot())) {
            parkingSlot = parkingSlotDao.findByIdOrSlotName(Integer.valueOf(bookingDTO.getSlot().toString()), "");
        } else {
            parkingSlot = parkingSlotDao.findByIdOrSlotName(-1, String.valueOf(bookingDTO.getSlot()));
        }
        if(parkingSlot.isPresent()) {
            parkingSlot.get().setOccupiedBy(null);
            parkingSlot.get().setVehicleNo(null);
            return parkingSlotDao.save(parkingSlot.get()).toData();
        } else {
            throw new ParkingSlotNotFoundException("Parking not found");
        }
    }

    private boolean isInteger(Object slotId) {
        boolean value = false;
        try {
            Integer.valueOf(slotId.toString());
            value = true;
        } catch (NumberFormatException ex) {
//            ignore, it must be string
        }
        return value;
    }
}

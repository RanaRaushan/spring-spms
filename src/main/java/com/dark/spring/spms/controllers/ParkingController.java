package com.dark.spring.spms.controllers;

import com.dark.spring.spms.data.ParkingData;
import com.dark.spring.spms.dto.BookingDTO;
import com.dark.spring.spms.dto.ParkingDTO;
import com.dark.spring.spms.service.ParkingService;
import com.dark.spring.spms.utils.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {


    @Autowired
    ParkingService parkingService;

    @Autowired
    CurrentUserService currentUserService;

    @GetMapping()
    public List<ParkingDTO> getAllParking(@RequestParam Map<String, Object> queryParams) {
        return parkingService.getAllParkingBy(queryParams).stream().map(ParkingData::toDTO).toList();
    }


    @GetMapping(value = "/{slotId}")
    public EntityModel<ParkingDTO> getParkingByIdOrSlotName(@PathVariable Object slotId) {
        ParkingData parkingData = parkingService.getParkingByIdOrSlotName(slotId);
        EntityModel<ParkingDTO> entityModel = EntityModel.of(parkingData.toDTO());

        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParking(HashMap.newHashMap(0)));
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));
        return entityModel;
    }

    @MessageMapping("/users/booking")
    @SendTo("/topic/booking_updates")
    public ParkingDTO parking_booking_update(@RequestBody BookingDTO bookingDTO) {
        System.out.println("Rana current User " + currentUserService.getCurrentUser());
        System.out.println("Rana calling parking_booking_update " + bookingDTO.toString());
        ParkingData parkingData = parkingService.updateParking(bookingDTO);
        ParkingDTO parkingDTO = parkingData.toDTO();
        parkingDTO.setEvent("Slot_Booking");
        parkingDTO.setEventMessage("Parking Slot Booked");
        return parkingDTO;
    }

    @MessageMapping("/users/release_booking")
    @SendTo("/topic/booking_updates")
    public ParkingDTO parking_booking_release(@RequestBody BookingDTO bookingDTO) {
        ParkingData parkingData = parkingService.emptyParkingSlot(bookingDTO);
        ParkingDTO parkingDTO = parkingData.toDTO();
        parkingDTO.setEvent("Release_Booking");
        parkingDTO.setEventMessage("Parking Slot Released");
        return parkingDTO;
    }
}

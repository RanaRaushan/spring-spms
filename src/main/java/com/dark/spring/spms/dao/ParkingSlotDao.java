package com.dark.spring.spms.dao;

import com.dark.spring.spms.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSlotDao extends JpaRepository<ParkingSlot, Integer>, JpaSpecificationExecutor<ParkingSlot> {

    Optional<ParkingSlot> getParkingById(int id);

    Optional<ParkingSlot> getParkingBySlotName(String slotName);

    Optional<ParkingSlot> findByIdOrSlotName(Integer slotId, String slotName);

}

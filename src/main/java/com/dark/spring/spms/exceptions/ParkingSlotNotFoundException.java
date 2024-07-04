package com.dark.spring.spms.exceptions;

public class ParkingSlotNotFoundException extends RuntimeException {

    public ParkingSlotNotFoundException(String msg) {
        super(msg);
    }
}
package com.example.fn;

import java.util.UUID;

/**
 *
 * @author davidd
 */
public class BookingConfirmation extends BookingRequest {

    private String confirmation;
    private int rate;

    public BookingConfirmation(String city, String date, String hotel, String coupon) {        
        super(city,date, hotel, coupon);
        this.confirmation = UUID.randomUUID().toString().substring(9, 23);
        this.rate = Coupon.validateRate(coupon);                
    }

    public String getConfirmation() {
        return confirmation;
    }

    public int getRate() {
        return rate;
    }

}

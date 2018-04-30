package com.delabassee.simple.service;

/**
 *
 * @author davidd
 */
public class BookingConfirmation extends BookingRequest {

    private String confirmation;
    private int rate;
    private String hotel;
    private String coupon;

    public BookingConfirmation() {}
    
    public BookingConfirmation(String city, String date) {
        super(city, date);
    }
    
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public int getRate() {
        return rate;
    }

    public String getHotel() {
        return hotel;
    }

    public String getCoupon() {
        return coupon;
    }

}

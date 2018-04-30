package com.example.fn;

/**
 *
 * @author davidd
 */
public class BookingRequest {
    
    String city;
    String date;
    String hotel;
    String coupon;
    int rate;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    public BookingRequest() {}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public BookingRequest(String city, String date, String hotel, String coupon) {
        this.city = city;
        this.date = date;
        this.hotel = hotel;
        this.coupon = coupon;
        this.rate = 0;
    }
    
    
    

    
}

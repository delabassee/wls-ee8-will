package com.delabassee.simple.service;

import java.util.Objects;
import javax.json.bind.JsonbBuilder;

/**
 *
 * @author davidd
 */
public class Hotel {

    private String city;
    private String date;
    private String hotel;
    private int rate;
    //private int rating;
    private String coupon;

    public Hotel() {
    }

    public Hotel(String city, String date, String hotel, String coupon) {
        this.city = city;
        this.date = date;
        this.hotel = hotel;
        this.coupon = coupon;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

/*    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }*/

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getHotel() {
        return hotel;
    }

    public int getRate() {
        return rate;
    }

/*    public int getRating() {
        return rating;
    }*/

    public String getCoupon() {
        return coupon;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Hotel other = (Hotel) obj;
        boolean isSame = (this.hotel == other.hotel) || (this.hotel != null && this.hotel.equalsIgnoreCase(other.hotel));

        return isSame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.date);
        return hash;
    }

    public String details() {
        return JsonbBuilder.create().toJson(this);
    }

    public String toJson() {
        return JsonbBuilder.create().toJson(this);

    }

}

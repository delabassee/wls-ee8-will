package com.delabassee.simple.service;

import javax.json.bind.JsonbBuilder;

/**
 *
 * @author davidd
 */
public class BookingRequest {

    private String date;
    private String city;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public BookingRequest() {
    }
    
    public BookingRequest(String city, String date) {
        this.date = date;
        this.city = city;
    }
    
    public String toJson() {
        return JsonbBuilder.create().toJson(this);

    }

}

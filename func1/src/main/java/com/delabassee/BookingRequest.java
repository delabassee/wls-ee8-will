package com.delabassee;

/**
 *
 * @author davidd
 */
public class BookingRequest {

    private String city;
    private String date;

    public BookingRequest() {}
    
    
    public BookingRequest(String city, String date) {
        this.city = city;
        this.date = date;
    }    

    
    public String getCity() {
        return city;
    }


    public String getDate() {
        return date;
    }


}
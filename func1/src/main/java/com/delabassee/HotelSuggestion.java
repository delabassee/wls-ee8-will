package com.delabassee;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author davidd
 */
public class HotelSuggestion {

    private String city;
    private String date;
    private String hotel;
    private int rate;
    private int rating;
    private String coupon;

    public HotelSuggestion() {
    }

    public HotelSuggestion(String city, String date) {
        this.city = city;        
        this.date = date;
        this.hotel = generatetName();
        this.rating = ThreadLocalRandom.current().nextInt(75, 96);
        int pseudoRate = ThreadLocalRandom.current().nextInt(7, 22);
        this.rate = ((pseudoRate * 10) - 1);
        this.coupon = Coupon.generate(rate, rating);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final HotelSuggestion other = (HotelSuggestion) obj;
        boolean isSame = (this.hotel == other.hotel) || (this.hotel != null && this.hotel.equalsIgnoreCase(other.hotel));

        return isSame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.date);
        return hash;
    }

    private String generatetName() {

        String[] hotels = {"Holliday Inn", "Radisson Blu", "Sofitel", "W", "Hilton", "Sheraton", "Melia", "Intercontinental", "Mercure", "Novotel", "Four Seasons", "Mariott", "Best Western", "Ascott", "Hyatt", "Mandarin", "NH", "Scandic"};
        String location = "";
        String prefix = "";

        int rndHotel = ThreadLocalRandom.current().nextInt(0, hotels.length);

        switch (ThreadLocalRandom.current().nextInt(1, 9)) {
            case 1:
                location = " Downtown";
                break;
            case 2:
                location = " Airport";
                break;
            case 3:
                location = " North";
                break;
            case 4:
                location = " South";
                break;
            case 5:
                location = " West";
                break;
            case 6:
                location = " East";
                break;
            case 7:
                location = " Convention Center";
                break;
            default:
                location = "";
                break;
        }

        int rnd = ThreadLocalRandom.current().nextInt(0, 10);
        
        if (rnd==0) prefix = "New ";
        else if (rnd < 5) prefix = this.city + " ";
        else prefix = "";
        
        return prefix + hotels[rndHotel] + location;
    }

    public int getRating() {
        return rating;
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
    
    public String getCoupon() {
        return coupon;
    }

}

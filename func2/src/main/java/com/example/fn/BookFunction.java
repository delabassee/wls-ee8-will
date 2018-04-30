package com.example.fn;

public class BookFunction {

    public BookingConfirmation handleRequest(BookingRequest input) {
        
        
        BookingConfirmation confirmation = new BookingConfirmation(input.getCity(), input.getDate(), input.getHotel(), input.getCoupon());

        return confirmation;
    }

}
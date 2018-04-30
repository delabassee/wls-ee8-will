package com.delabassee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GetHotelFunction {

    public List<HotelSuggestion> handleRequest(BookingRequest input) {

        List<HotelSuggestion> suggestions = new ArrayList<>();
        int rnd = ThreadLocalRandom.current().nextInt(3,7);
        
        for (int i = 0; i < rnd; i++) {
            suggestions.add(new HotelSuggestion(input.getCity(), input.getDate()));
        }               
        
        List<HotelSuggestion> filteredSuggestions = suggestions.stream()
                .distinct()
                .collect(Collectors.toList());
        
        return filteredSuggestions;        
    }       

}

/*
class Result {

    private int unfilter;
    private int filtered;
    private List<HotelSuggestion> suggestions;

    public Result(int unfilter, int filtered, List<HotelSuggestion> suggestions) {
        this.unfilter = unfilter;
        this.filtered = filtered;
        this.suggestions = suggestions;
    }
    
    public Result() {}

    public int getUnfilter() {
        return unfilter;
    }

    public int getFiltered() {
        return filtered;
    }

    public List<HotelSuggestion> getSuggestions() {
        return suggestions;
    }             
    
}   */ 


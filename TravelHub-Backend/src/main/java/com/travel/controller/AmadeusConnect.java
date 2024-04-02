package com.travel.controller;

import java.util.ArrayList;
import java.util.List;

import com.amadeus.Amadeus;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;


import com.amadeus.referenceData.Locations;
import com.amadeus.referenceData.locations.Hotel;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.HotelOfferSearch;
import com.amadeus.resources.Location;


public class AmadeusConnect {
    private static final String API_KEY = "eRysiLe5vCLsdIG6Tpf1t2oELHGXzANP";
    private static final String API_SECRET = "mCs9XwCLqaBxh7sm";

    private static Amadeus amadeus;

    static {
        amadeus = Amadeus.builder(API_KEY, API_SECRET).build();
    }

    public static Location[] searchLocations(String keyword) throws ResponseException {
        return amadeus.referenceData.locations.get(Params.with("keyword", keyword).and("subType", Locations.AIRPORT));
    }

    public static FlightOfferSearch[] searchFlights(String origin, String destination, String departDate, String adults, String returnDate) throws ResponseException {
        return amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", origin)
                        .and("destinationLocationCode", destination)
                        .and("departureDate", departDate)
                        .and("returnDate", returnDate)
                        .and("adults", adults)
                        );
    }
    public static HotelOfferSearch[] searchHotelById(String hotelId,int adult,String checkInDate) throws ResponseException {
        return amadeus.shopping.hotelOffersSearch.get(
                Params.with("hotelIds", hotelId)
                .and("checkIn", checkInDate)
                .and("adult", adult));
    }
    public static List<String> searchHotelIdsByCity(String cityCode, double radius) throws ResponseException {
        List<String> hotelIds = new ArrayList<>();
        
        // Fetch hotels by city and radius
        com.amadeus.resources.Hotel[] hotels = amadeus.referenceData.locations.hotels.byCity.get(
            Params.with("cityCode", cityCode)
                .and("radius", radius)
        );

        // Extract hotel IDs
        for (com.amadeus.resources.Hotel hotel : hotels) {
            hotelIds.add(hotel.getHotelId());
        }

        return hotelIds;
    }
    
}

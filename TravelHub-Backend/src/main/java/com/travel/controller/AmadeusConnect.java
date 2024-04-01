package com.travel.controller;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.amadeus.exceptions.ResponseException;

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
}

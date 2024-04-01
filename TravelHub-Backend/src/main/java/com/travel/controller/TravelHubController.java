package com.travel.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.amadeus.exceptions.ResponseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.model.Bus;
import com.travel.model.FlightBookingDetails;
import com.travel.model.Review;
import com.travel.model.Train;
import com.travel.model.User;
import com.travel.service.TravelHubService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TravelHubController {
	 @Autowired
	 TravelHubService service;
	 @PostMapping("/register")
     public User registerUser(@RequestBody User user) throws Exception  {
  	   String tempemail=user.getEmail();
  	   if(tempemail!=null && !"".equals(tempemail)) {
  		   User userobj=service.fetchemail(tempemail);
  		   if(userobj!=null) {
  			   throw new Exception("user with "+tempemail+" already exits");
  		   }
  	   }
  	   User userobj=null;
  	   if(user != null) {
  		   userobj=service.saveUser(user);
  	   }
  	   return userobj;
    }
	 @PostMapping("/login")
	   public User login(@RequestBody User user) throws Exception {
		   String tempemail=user.getEmail();
		   String temppass=user.getPassword();
		   User userobj=null;
		   if(tempemail!=null &&temppass!=null) {
			   userobj=service.fetchuseremailandpassword(tempemail, temppass);
		   }
		   if(userobj==null) {
			   throw new Exception("Bad Credentials");
		   }
		   return userobj;   
	   }
	@PostMapping("/saveReview")
	   public Review saveReview(@RequestBody Review review) {
		   User user= service.fetchemail(review.getReviewemail());
		   review.setUserDetails(user);
		   return service.saveReview(review);
	   }
	 @GetMapping("/getreview")
	   public List<Review> getReviews(){
		   return service.getReview();
	   }
	@GetMapping("/health")
    public String healthCheck() {
        return "Application is running!";
    }
	@GetMapping("/searchLocations")
	public ResponseEntity<List<String>> searchLocations(@RequestParam String keyword) {
	    try {
	        Location[] locations = AmadeusConnect.searchLocations(keyword);
	        List<String> locationStrings = new ArrayList<>();
	        for (Location location : locations) {
	            locationStrings.add(formatLocation(location));
	        }
	        return ResponseEntity.ok(locationStrings);
	    } catch (ResponseException e) {
	        e.printStackTrace(); // Log the exception for debugging
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Collections.singletonList("An error occurred while processing your request."));
	    }
	}

	private String formatLocation(Location location) {
	    return String.format("Name: %s\nDetailed Name: %s\nIATA Code: %s\nCity: %s\nCountry: %s\nRegion Code: %s\nLatitude: %s\nLongitude: %s\nTime Zone Offset: %s\nTravelers Score: %s",
	            location.getName(), location.getDetailedName(), location.getIataCode(),
	            location.getAddress().getCityName(), location.getAddress().getCountryName(),
	            location.getAddress().getRegionCode(), location.getGeoCode().getLatitude(),
	            location.getGeoCode().getLongitude(), location.getTimeZoneOffset(),
	            location.getAnalytics().getTravelers().getScore());
	}

	@GetMapping("/searchFlights")
	public ResponseEntity<?> searchFlights(@RequestParam String origin,
	                                       @RequestParam String destination,
	                                       @RequestParam String departDate,
	                                       @RequestParam String adults,
	                                       @RequestParam(required = false) String returnDate) {
	    try {
	        FlightOfferSearch[] flightOffers = AmadeusConnect.searchFlights(origin, destination, departDate, adults, returnDate);
	        
	        Map<Double, FlightBookingDetails> uniqueFlightOffers = new HashMap<>();
	        List<FlightBookingDetails> flightBookingDetailsList = new ArrayList<>();

	        for (FlightOfferSearch flightOffer : flightOffers) {
	            double totalPrice = calculateTotalPrice(flightOffer);
	            if (!uniqueFlightOffers.containsKey(totalPrice)) {
	                FlightBookingDetails bookingDetails = extractBookingDetails(flightOffer);
	                flightBookingDetailsList.add(bookingDetails);
	                uniqueFlightOffers.put(totalPrice, bookingDetails);
	            }
	        }
	        
	        return ResponseEntity.ok(flightBookingDetailsList);
	    } catch (ResponseException e) {
	        e.printStackTrace(); // Log the exception for debugging
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while processing your request.");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest()
	                .body("Invalid request parameters. Please check your input.");
	    } 
	}

	private double calculateTotalPrice(FlightOfferSearch flightOffer) {
	    double totalPrice = 0.0;
	    for (FlightOfferSearch.TravelerPricing travelerPricing : flightOffer.getTravelerPricings()) {
	        totalPrice += travelerPricing.getPrice().getTotal();
	    }
	    return totalPrice;
	}

	private FlightBookingDetails extractBookingDetails(FlightOfferSearch flightOffer) {
	    FlightBookingDetails bookingDetails = new FlightBookingDetails();

	    // Extract relevant information for traveler booking
	    bookingDetails.setAirline(flightOffer.getValidatingAirlineCodes()[0]); // Assuming only one airline code
	    bookingDetails.setInstantTicketingRequired(flightOffer.isInstantTicketingRequired());
	    bookingDetails.setNonHomogeneous(flightOffer.isNonHomogeneous());
	    bookingDetails.setOneWay(flightOffer.isOneWay());
	    bookingDetails.setLastTicketingDate(flightOffer.getLastTicketingDate());
	    bookingDetails.setNumberOfBookableSeats(flightOffer.getNumberOfBookableSeats());
	    bookingDetails.setChoiceProbability(flightOffer.getChoiceProbability());

	    // Extract itinerary details
	    List<FlightBookingDetails.ItineraryDetails> itineraryDetailsList = new ArrayList<>();
	    for (FlightOfferSearch.Itinerary itinerary : flightOffer.getItineraries()) {
	        FlightBookingDetails.ItineraryDetails itineraryDetails = new FlightBookingDetails.ItineraryDetails();
	        itineraryDetails.setDuration(itinerary.getDuration());

	        List<FlightBookingDetails.SegmentDetails> segmentDetailsList = new ArrayList<>();
	        for (FlightOfferSearch.SearchSegment segment : itinerary.getSegments()) {
	            FlightBookingDetails.SegmentDetails segmentDetails = new FlightBookingDetails.SegmentDetails();
	            segmentDetails.setCarrierCode(segment.getCarrierCode());
	            segmentDetails.setNumber(segment.getNumber());
	            segmentDetails.setDuration(segment.getDuration());
	            segmentDetails.setNumberOfStops(segment.getNumberOfStops());
	            segmentDetails.setBlacklistedInEU(segment.isBlacklistedInEU());

	            // Extract departure airport information
	            FlightBookingDetails.AirportInfo departureAirport = new FlightBookingDetails.AirportInfo();
	            departureAirport.setIataCode(segment.getDeparture().getIataCode());
	            departureAirport.setTerminal(segment.getDeparture().getTerminal());
	            departureAirport.setAt(segment.getDeparture().getAt());
	            segmentDetails.setDepartureAirport(departureAirport);

	            // Extract arrival airport information
	            FlightBookingDetails.AirportInfo arrivalAirport = new FlightBookingDetails.AirportInfo();
	            arrivalAirport.setIataCode(segment.getArrival().getIataCode());
	            arrivalAirport.setTerminal(segment.getArrival().getTerminal());
	            arrivalAirport.setAt(segment.getArrival().getAt());
	            segmentDetails.setArrivalAirport(arrivalAirport);

	            // Extract aircraft information
	            FlightBookingDetails.Aircraft aircraft = new FlightBookingDetails.Aircraft();
	            aircraft.setCode(segment.getAircraft().getCode());
	            segmentDetails.setAircraft(aircraft);

	            // Extract CO2 emissions
				/*
				 * List<FlightBookingDetails.Co2Emissions> co2EmissionsList = new ArrayList<>();
				 * for (FlightOfferSearch.Co2Emissions co2Emission : segment.getCo2Emissions())
				 * { FlightBookingDetails.Co2Emissions emissions = new
				 * FlightBookingDetails.Co2Emissions();
				 * emissions.setWeight(co2Emission.getWeight());
				 * emissions.setWeightUnit(co2Emission.getWeightUnit());
				 * emissions.setCabin(co2Emission.getCabin()); co2EmissionsList.add(emissions);
				 * } segmentDetails.setCo2Emissions(co2EmissionsList);
				 * 
				 * segmentDetailsList.add(segmentDetails);
				 */
	        }
	        itineraryDetails.setSegments(segmentDetailsList);
	        itineraryDetailsList.add(itineraryDetails);
	    }
	    bookingDetails.setItineraries(itineraryDetailsList);

	    // Extract pricing options
	    FlightOfferSearch.PricingOptions pricingOptions = flightOffer.getPricingOptions();
	    if (pricingOptions != null) {
	        bookingDetails.setIncludedCheckedBagsOnly(pricingOptions.isIncludedCheckedBagsOnly());
	        bookingDetails.setFareType(pricingOptions.getFareType());
	        bookingDetails.setCorporateCodes(pricingOptions.getCorporateCodes());
	        bookingDetails.setRefundableFare(pricingOptions.isRefundableFare());
	        bookingDetails.setNoRestrictionFare(pricingOptions.isNoRestrictionFare());
	        bookingDetails.setNoPenaltyFare(pricingOptions.isNoPenaltyFare());
	    }

	    // Extract traveler pricings
	    List<FlightBookingDetails.TravelerPricing> travelerPricingsList = new ArrayList<>();
	    for (FlightOfferSearch.TravelerPricing travelerPricing : flightOffer.getTravelerPricings()) {
	        FlightBookingDetails.TravelerPricing pricing = new FlightBookingDetails.TravelerPricing();
	        pricing.setTravelerId(travelerPricing.getTravelerId());
	        pricing.setFareOption(travelerPricing.getFareOption());
	        pricing.setTravelerType(travelerPricing.getTravelerType());
	        pricing.setPrice(FlightBookingDetails.convertSearchPrice(travelerPricing.getPrice()));

	        // Extract fare details by segment
	        List<FlightBookingDetails.FareDetailsBySegment> fareDetailsList = new ArrayList<>();
	        for (FlightOfferSearch.FareDetailsBySegment fareDetails : travelerPricing.getFareDetailsBySegment()) {
	            FlightBookingDetails.FareDetailsBySegment fareDetailsBySegment = new FlightBookingDetails.FareDetailsBySegment();
	            fareDetailsBySegment.setSegmentId(fareDetails.getSegmentId());
	            fareDetailsBySegment.setCabin(fareDetails.getCabin());
	            fareDetailsBySegment.setFareBasis(fareDetails.getFareBasis());
	            fareDetailsBySegment.setSegmentClass(fareDetails.getSegmentClass());
	            fareDetailsBySegment.setIncludedCheckedBags(fareDetails.getIncludedCheckedBags());
	            fareDetailsList.add(fareDetailsBySegment);
	        }
	        pricing.setFareDetailsBySegment(fareDetailsList);
	        travelerPricingsList.add(pricing);
	    }
	    bookingDetails.setTravelerPricings(travelerPricingsList);

	    return bookingDetails;  
	 
	}
	@PostMapping("/saveTrain")
	public Train savetrain (@RequestBody Train train) {
		   return service.saveTrain(train);
	}
	@PostMapping("/saveBus")
	public Bus savebus (@RequestBody Bus bus) {
		   return service.saveBus(bus);
	}
	@PostMapping("/trains")
	public List<Train> searchTrains(@RequestBody Map<String, Object> requestMap) {
	    String departureStation = (String) requestMap.get("departureStation");
	    String arrivalStation = (String) requestMap.get("arrivalStation");
	    String departureDateString = (String) requestMap.get("departureDate");

	    // Parse the departureDateString to LocalDate
	    LocalDate departureDate = LocalDate.parse(departureDateString);

	    return service.searchtrains(departureStation, arrivalStation, departureDate);
	}
	@PostMapping("/buses")
	public List<Bus> searchBuses(@RequestBody Map<String, Object> requestMap) {
	    String departureTerminal = (String) requestMap.get("departureTerminal");
	    String arrivalTerminal = (String) requestMap.get("arrivalTerminal");
	    String departureDateString = (String) requestMap.get("departureDate");

	    // Parse the departureDateString to LocalDate
	    LocalDate departureDate = LocalDate.parse(departureDateString);

	    return service.searchBuses(departureTerminal, arrivalTerminal, departureDate);
	}

}

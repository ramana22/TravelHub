package com.travel.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.HotelOfferSearch.Offer;
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
import com.travel.model.*;
import com.travel.service.EmailService;
import com.travel.service.TravelHubService;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TravelHubController {
	 @Autowired
	 TravelHubService service;
	 @Autowired
	 EmailService emailservice;
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
	@PostMapping("/saveprofile")
	public profile saveprofile(@RequestBody  profile user) throws Exception {
		String tempemail=user.getEmail();
		User userobj=null;
		profile prof=null;
		if(tempemail!=null) {
			userobj=service.fetchemail(tempemail);
		}
		if(userobj==null) {
			throw new Exception("Bad Credentials");
		}
		else {
			prof=service.saveprofile(user); 
		}
		return  prof; 
	}
	@GetMapping("/getprofile")
	public profile getProfile(@RequestParam String email) throws Exception {
		profile prof = service.fetchprofile(email);
	    if (prof == null) {
	        throw new Exception("Profile not found for email: " + email);
	    }
	    return prof;
	}

	@PostMapping("/change")
	 public User changePassword(@RequestBody Map<String, String> body) throws Exception {
	   String tempemail= body.get("email");
	   String temppass = body.get("oldpass");
       String newpass = body.get("newpass");
 	   User userobj=null;
 	   if(tempemail!=null && !"".equals(tempemail) && temppass!=null) {
 		   userobj=service.fetchemail(tempemail);
 		   if(userobj!=null) {
 			   userobj.setPassword(newpass);  
 		   }
 	   }
 	   return service.saveUser(userobj);
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
	@GetMapping("/searchFlightsoneway")
	public ResponseEntity<?> searchFlightsoneway(@RequestParam String origin,
	                                       @RequestParam String destination,
	                                       @RequestParam String departDate,
	                                       @RequestParam String adults) {
	    try {
	        FlightOfferSearch[] flightOffers = AmadeusConnect.searchFlightsoneway(origin, destination, departDate, adults);
	        
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
	        service.saveAllflights(flightBookingDetailsList);
	        
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
	        String totalAsString = travelerPricing.getPrice().getTotal();
	        double totalAsDouble = Double.parseDouble(totalAsString);
	        totalPrice += totalAsDouble;
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
	@PostMapping("/saveCar")
	public Car saveCar (@RequestBody Car car) {
		   return service.saveCar(car);
	}
	@PostMapping("/saveRestaurant")
	public Restaurant saveRestaurant (@RequestBody Restaurant restaurant) {
		   return service.saveRestaurant(restaurant);
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
	@PostMapping("/cars")
	public List<Car> searchCars(@RequestBody Map<String, Object> requestMap) {
	    String pickupLocation = (String) requestMap.get("pickupLocation");
	    String rentalStartDateString = (String) requestMap.get("rentalStartDate");
	    String rentalEndDateString = (String) requestMap.get("rentalEndDate");

	    // Parse the rentalStartDate and rentalEndDate to LocalDate
	    LocalDate rentalStartDate = LocalDate.parse(rentalStartDateString);
	    LocalDate rentalEndDate = LocalDate.parse(rentalEndDateString);
	    System.out.print(pickupLocation);
	    System.out.print(rentalStartDate);
	    System.out.print(rentalEndDate);
	    
	    

	    return service.searchCars(pickupLocation, rentalStartDate, rentalEndDate);
	}

	
    
	@GetMapping("/hotels/search")
	public List<HotelOfferResponse.HotelOffer> searchHotels(@RequestParam String hotelId,@RequestParam String checkInDate,int adult) {
	    List<HotelOfferResponse.HotelOffer> hotelOfferList = new ArrayList<>();
	    try {
	        com.amadeus.resources.HotelOfferSearch[] hotelOfferSearchArray = AmadeusConnect.searchHotelById(hotelId,adult,checkInDate);
	        for (com.amadeus.resources.HotelOfferSearch offerSearch : hotelOfferSearchArray) {
	            HotelOfferResponse.HotelOffer hotelOffer = new HotelOfferResponse.HotelOffer();
	            
	            // Set hotel details
	            HotelOfferResponse.Hotel hotel = new HotelOfferResponse.Hotel();
	            hotel.setType("hotel");
	            hotel.setHotelId(offerSearch.getHotel().getHotelId());
	            hotel.setChainCode(offerSearch.getHotel().getChainCode());
	            hotel.setDupeId(offerSearch.getHotel().getDupeId());
	            hotel.setName(offerSearch.getHotel().getName());
	            hotel.setCityCode(offerSearch.getHotel().getCityCode());
	            hotelOffer.setHotel(hotel);
	            
	            // Set offer details
	            List<HotelOfferResponse.Offer> offers = new ArrayList<>();
	            for (Offer offer : offerSearch.getOffers()) {
	                HotelOfferResponse.Offer hotelOfferOffer = new HotelOfferResponse.Offer();
	                hotelOfferOffer.setId(offer.getId());
	                hotelOfferOffer.setCheckInDate(offer.getCheckInDate());
	                hotelOfferOffer.setCheckOutDate(offer.getCheckOutDate());
	                // Set other offer properties similarly
	                
	                // Set rate family estimated
	                if (offer.getRateFamilyEstimated() != null) {
	                    HotelOfferResponse.RateFamilyEstimated rateFamilyEstimated = new HotelOfferResponse.RateFamilyEstimated();
	                    rateFamilyEstimated.setCode(offer.getRateFamilyEstimated().getCode());
	                    rateFamilyEstimated.setType(offer.getRateFamilyEstimated().getType());
	                    hotelOfferOffer.setRateFamilyEstimated(rateFamilyEstimated);
	                }
	                
	                // Set room
	                if (offer.getRoom() != null) {
	                    HotelOfferResponse.Room room = new HotelOfferResponse.Room();
	                    room.setType(offer.getRoom().getType());
	                    
	                    // Set type estimated
	                    if (offer.getRoom().getTypeEstimated() != null) {
	                        HotelOfferResponse.TypeEstimated typeEstimated = new HotelOfferResponse.TypeEstimated();
	                        typeEstimated.setCategory(offer.getRoom().getTypeEstimated().getCategory());
	                        
	                        // Check if beds is null before accessing it
	                        Integer beds = offer.getRoom().getTypeEstimated().getBeds();
	                        if (beds != null) {
	                            typeEstimated.setBeds(beds);
	                        } else {
	                            // Handle the case when beds is null, you can set a default value or handle it according to your application's logic
	                            typeEstimated.setBeds(0); // Setting a default value of 0 beds
	                        }
	                        
	                        typeEstimated.setBedType(offer.getRoom().getTypeEstimated().getBedType());
	                        room.setTypeEstimated(typeEstimated);
	                    }
	                    
	                    // Set description
	                    if (offer.getRoom().getDescription() != null) {
	                        HotelOfferResponse.Description description = new HotelOfferResponse.Description();
	                        description.setText(offer.getRoom().getDescription().getText());
	                        description.setLang(offer.getRoom().getDescription().getLang());
	                        room.setDescription(description);
	                    }
	                    
	                    hotelOfferOffer.setRoom(room);
	                }
	                
	                // Set guests
	                if (offer.getGuests() != null) {
	                    HotelOfferResponse.Guests guests = new HotelOfferResponse.Guests();
	                    guests.setAdults(offer.getGuests().getAdults());
	                    // Set other guest properties similarly
	                    hotelOfferOffer.setGuests(guests);
	                }
	                
	                // Set price
	                if (offer.getPrice() != null) {
	                    HotelOfferResponse.Price price = new HotelOfferResponse.Price();
	                    price.setCurrency(offer.getPrice().getCurrency());
	                    price.setBase(offer.getPrice().getBase());
	                    price.setTotal(offer.getPrice().getTotal());
	                    // Set variations and other price properties similarly
	                    hotelOfferOffer.setPrice(price);
	                }
	                
	                // Set policies
	                if (offer.getPolicies() != null) {
	                    HotelOfferResponse.Policies policies = new HotelOfferResponse.Policies();
	                    // Set policies properties similarly
	                    hotelOfferOffer.setPolicies(policies);
	                }
	                
	                // Set self
	                if (offer.getSelf() != null) {
	                    hotelOfferOffer.setSelf(offer.getSelf());
	                }
	                
	                offers.add(hotelOfferOffer);
	            }
	            hotelOffer.setOffers(offers);
	            
	            hotelOfferList.add(hotelOffer);
	        }
	    } catch (ResponseException e) {
	        e.printStackTrace();
	        // Handle the exception or return appropriate response
	    }
	    return hotelOfferList;
	}
	@GetMapping("/hotels")
	public List<HotelOfferResponse.HotelOffer> getHotelIdsByCity(
	        @RequestParam String cityCode,
	        @RequestParam String checkInDate,
	        @RequestParam int adult,
	        @RequestParam(required = false) Double radius
	) {
	    try {
	        List<String> hotelIds;
	        if (radius != null) {
	            hotelIds = AmadeusConnect.searchHotelIdsByCity(cityCode, radius);
	            // Limit the number of hotel IDs retrieved to a maximum of 5
	            hotelIds = hotelIds.subList(0, Math.min(hotelIds.size(), 30));
	        } else {
	            hotelIds = new ArrayList<>(); // Initialize with an empty list
	        }
	        
	        System.out.println(hotelIds);
	        List<HotelOfferResponse.HotelOffer> hotelResults = new ArrayList<>();
	        for (String hotelId : hotelIds) {
	            try {
	                // Call the searchHotels method passing the hotelId
	                List<HotelOfferResponse.HotelOffer> hotelOffers = searchHotels(hotelId,checkInDate,adult);
	                hotelResults.addAll(hotelOffers);
	            } catch (Exception e) {
	                // Log the error or handle it as needed
	                System.err.println("Error occurred while searching for hotel with ID " + hotelId + ": " + e.getMessage());
	                // Continue to the next hotel ID if any error occurs
	                continue;
	            }
	        }
	        return hotelResults;
	    } catch (Exception e) {
	        // Handle the exception or return appropriate response
	        e.printStackTrace();
	        return null;
	    }
	}
	@PostMapping("/getfilterlist")
	   public List<Object> filter(@RequestBody Map<String, String> requestBody) {
	        String city = requestBody.get("city");

	        List<Object> filteredHotels = new ArrayList<>();
	        List<Hotel> hotels = service.getAllHotels();
	        for (Hotel hotel : hotels) {
	            String hotelCity = hotel.getAddress().getCity();

	            if (hotelCity != null && hotelCity.equalsIgnoreCase(city.trim())) {
	                filteredHotels.add(hotel);
	            }
	        }
	        return filteredHotels;
	  }
	@PostMapping("/addHotel")
	public Hotel addHotel(@RequestBody Hotel hotel) {   
	       Hotel savedHotel = service.saveHotel(hotel);
	       return savedHotel;
	}
	@GetMapping("/searchLocations")
    public ResponseEntity<List<LocationData>> searchLocations(@RequestParam String keyword) {
        try {
            Location[] locations = AmadeusConnect.searchLocations(keyword);
            List<LocationData> locationDataList = new ArrayList<>();
            for (Location location : locations) {
                locationDataList.add(mapToLocationData(location));
            }
            service.saveAll(locationDataList);
            return ResponseEntity.ok(locationDataList);
        } catch (ResponseException e) {
            e.printStackTrace(); // Log the exception for debugging
            return null;
        }
    }

	public LocationData mapToLocationData(Location location) {
        LocationData locationData = new LocationData();
        locationData.setName(location.getName());
        locationData.setDetailedName(location.getDetailedName());
        locationData.setIataCode(location.getIataCode());

        // Create and set the Address
        
        locationData.setCityName(location.getAddress().getCityName());
        locationData.setCountryName(location.getAddress().getCountryName());
        locationData.setRegionCode(location.getAddress().getRegionCode());

        locationData.setTimeZoneOffset(location.getTimeZoneOffset());

        return locationData;
    }
	@PostMapping("/saveBusTicket")
	public BusTicket savebusticket(@RequestBody BusTicket busticket, @RequestParam String userEmail) {
	    // Fetch user object by email
	    User user = service.fetchemail(userEmail);
	    
	    // Set the fetched user object to the bus ticket
	    busticket.setUser(user);
	    
	    // Save the bus ticket
	    return service.savebusticket(busticket);
	}
	@PostMapping("/saveTrainTicket")
    public TrainTicket saveTrainTicket(@RequestBody TrainTicket trainTicket, @RequestParam String userEmail) {
        // Fetch user object by email
        User user = service.fetchemail(userEmail);
        System.out.println(user.toString());
        // Set the fetched user object to the train ticket
        trainTicket.setUser(user);

        // Save the train ticket
        return service.savetrainticket(trainTicket);
    }
	@PostMapping("/saveFlightTicket")
    public FlightTicket saveFlightTicket(@RequestBody FlightTicket flightTicket, @RequestParam String userEmail) {
        // Fetch user object by email
        User user = service.fetchemail(userEmail);
        System.out.println(user.toString());

        // Set the fetched user object to the train ticket
        flightTicket.setUser(user);

        // Save the train ticket
        return service.saveflightticket(flightTicket);
    }
	@PostMapping("/saveHotelBooking")
    public HotelBooking saveHotelBooking(@RequestBody HotelBooking hotelBooking, @RequestParam String userEmail) {
        // Fetch user object by email
        User user = service.fetchemail(userEmail);

        // Set the fetched user object to the hotel booking
        hotelBooking.setUser(user);
        System.out.println(hotelBooking.toString());
        // Save the hotel booking
        return service.savehotelbooking(hotelBooking);
    }
	@PostMapping("/savecarBooking")
    public CarBooking saveCarBooking(@RequestBody CarBooking carBooking, @RequestParam String userEmail) {
        // Fetch user object by email
        User user = service.fetchemail(userEmail);

        // Set the fetched user object to the hotel booking
        carBooking.setUser(user);

        // Save the hotel booking
        return service.savecarbooking(carBooking);
    }
	@PostMapping("/getfilterRestaurant")
	public List<Restaurant> filterrestaurant(@RequestBody Map<String, String> requestBody) {
	        String city = requestBody.get("city");
	        List<Restaurant> filteredRestaurants = new ArrayList<>();
	        List<Restaurant> restaurants= service.getallrestaurants();
	        for (Restaurant restaurant : restaurants) {
	            String restaurantCity = restaurant.getAddress().getCity();

	            if (restaurantCity != null && restaurantCity.equalsIgnoreCase(city.trim())) {
	            	filteredRestaurants.add(restaurant);
	            }
	        }
	        return filteredRestaurants;
	}
	 @PostMapping("/sendEmail")
	 public void sendEmail(@RequestBody EmailRequest emailRequest) {
	     String to = emailRequest.getTo();
	     String subject = emailRequest.getSubject();
	     String text = emailRequest.getText();

	     emailservice.sendEmail(to, subject, text);
	}
	 
}

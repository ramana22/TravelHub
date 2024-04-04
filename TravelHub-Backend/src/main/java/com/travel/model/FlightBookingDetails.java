package com.travel.model;

import jakarta.persistence.*;
import java.util.List;

import com.amadeus.resources.FlightOfferSearch;

@Entity
@Table(name = "flight_booking_details")
public class FlightBookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "airline")
    private String airline;

    @Column(name = "instant_ticketing_required")
    private boolean instantTicketingRequired;

    @Column(name = "non_homogeneous")
    private boolean nonHomogeneous;

    @Column(name = "one_way")
    private boolean oneWay;

    @Column(name = "last_ticketing_date")
    private String lastTicketingDate;

    @Column(name = "number_of_bookable_seats")
    private int numberOfBookableSeats;

    @Column(name = "choice_probability")
    private String choiceProbability;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    private List<ItineraryDetails> itineraries;

    @Column(name = "included_checked_bags_only")
    private boolean includedCheckedBagsOnly;

    @ElementCollection
    @Column(name = "fare_type")
    private String[] fareType;

    @ElementCollection
    @Column(name = "corporate_codes")
    private String[] corporateCodes;

    @Column(name = "refundable_fare")
    private boolean refundableFare;

    @Column(name = "no_restriction_fare")
    private boolean noRestrictionFare;

    @Column(name = "no_penalty_fare")
    private boolean noPenaltyFare;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "traveler_pricing_id")
    private List<TravelerPricing> travelerPricings;

    // Nested Classes

    @Entity
    @Table(name = "itinerary_details")
    public static class ItineraryDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "duration")
        private String duration;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "segment_details_id")
        private List<SegmentDetails> segments;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public List<SegmentDetails> getSegments() {
			return segments;
		}

		public void setSegments(List<SegmentDetails> segments) {
			this.segments = segments;
		}

        
    }

    @Entity
    @Table(name = "segment_details")
    public static class SegmentDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "carrier_code")
        private String carrierCode;

        @Column(name = "number")
        private String number;

        @Column(name = "duration")
        private String duration;

        @Column(name = "number_of_stops")
        private int numberOfStops;

        @Column(name = "blacklisted_in_eu")
        private boolean blacklistedInEU;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "departure_airport_id")
        private AirportInfo departureAirport;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "arrival_airport_id")
        private AirportInfo arrivalAirport;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "aircraft_id")
        private Aircraft aircraft;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "co2_emissions_id")
        private List<Co2Emissions> co2Emissions;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCarrierCode() {
			return carrierCode;
		}

		public void setCarrierCode(String carrierCode) {
			this.carrierCode = carrierCode;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public int getNumberOfStops() {
			return numberOfStops;
		}

		public void setNumberOfStops(int numberOfStops) {
			this.numberOfStops = numberOfStops;
		}

		public boolean isBlacklistedInEU() {
			return blacklistedInEU;
		}

		public void setBlacklistedInEU(boolean blacklistedInEU) {
			this.blacklistedInEU = blacklistedInEU;
		}

		public AirportInfo getDepartureAirport() {
			return departureAirport;
		}

		public void setDepartureAirport(AirportInfo departureAirport) {
			this.departureAirport = departureAirport;
		}

		public AirportInfo getArrivalAirport() {
			return arrivalAirport;
		}

		public void setArrivalAirport(AirportInfo arrivalAirport) {
			this.arrivalAirport = arrivalAirport;
		}

		public Aircraft getAircraft() {
			return aircraft;
		}

		public void setAircraft(Aircraft aircraft) {
			this.aircraft = aircraft;
		}

		public List<Co2Emissions> getCo2Emissions() {
			return co2Emissions;
		}

		public void setCo2Emissions(List<Co2Emissions> co2Emissions) {
			this.co2Emissions = co2Emissions;
		}

        
    }

    @Entity
    @Table(name = "airport_info")
    public static class AirportInfo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "iata_code")
        private String iataCode;

        @Column(name = "terminal")
        private String terminal;

        @Column(name = "at")
        private String at;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getIataCode() {
			return iataCode;
		}

		public void setIataCode(String iataCode) {
			this.iataCode = iataCode;
		}

		public String getTerminal() {
			return terminal;
		}

		public void setTerminal(String terminal) {
			this.terminal = terminal;
		}

		public String getAt() {
			return at;
		}

		public void setAt(String at) {
			this.at = at;
		}

        
    }

    @Entity
    @Table(name = "aircraft")
    public static class Aircraft {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "code")
        private String code;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

       
    }

    @Entity
    @Table(name = "co2_emissions")
    public static class Co2Emissions {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "weight")
        private int weight;

        @Column(name = "weight_unit")
        private String weightUnit;

        @Column(name = "cabin")
        private String cabin;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public String getWeightUnit() {
			return weightUnit;
		}

		public void setWeightUnit(String weightUnit) {
			this.weightUnit = weightUnit;
		}

		public String getCabin() {
			return cabin;
		}

		public void setCabin(String cabin) {
			this.cabin = cabin;
		}

        
    }

    @Entity
    @Table(name = "traveler_pricing")
    public static class TravelerPricing {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "traveler_id")
        private String travelerId;

        @Column(name = "fare_option")
        private String fareOption;

        @Column(name = "traveler_type")
        private String travelerType;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "search_price_id")
        private SearchPrice price;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "fare_details_by_segment_id")
        private List<FareDetailsBySegment> fareDetailsBySegment;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTravelerId() {
			return travelerId;
		}

		public void setTravelerId(String travelerId) {
			this.travelerId = travelerId;
		}

		public String getFareOption() {
			return fareOption;
		}

		public void setFareOption(String fareOption) {
			this.fareOption = fareOption;
		}

		public String getTravelerType() {
			return travelerType;
		}

		public void setTravelerType(String travelerType) {
			this.travelerType = travelerType;
		}

		public SearchPrice getPrice() {
			return price;
		}

		public void setPrice(SearchPrice price) {
			this.price = price;
		}

		public List<FareDetailsBySegment> getFareDetailsBySegment() {
			return fareDetailsBySegment;
		}

		public void setFareDetailsBySegment(List<FareDetailsBySegment> fareDetailsBySegment) {
			this.fareDetailsBySegment = fareDetailsBySegment;
		}

        
    }

    @Entity
    @Table(name = "fare_details_by_segment")
    public static class FareDetailsBySegment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "segment_id")
        private String segmentId;

        @Column(name = "cabin")
        private String cabin;

        @Column(name = "fare_basis")
        private String fareBasis;

        @Column(name = "segment_class")
        private String segmentClass;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSegmentId() {
			return segmentId;
		}

		public void setSegmentId(String segmentId) {
			this.segmentId = segmentId;
		}

		public String getCabin() {
			return cabin;
		}

		public void setCabin(String cabin) {
			this.cabin = cabin;
		}

		public String getFareBasis() {
			return fareBasis;
		}

		public void setFareBasis(String fareBasis) {
			this.fareBasis = fareBasis;
		}

		public String getSegmentClass() {
			return segmentClass;
		}

		public void setSegmentClass(String segmentClass) {
			this.segmentClass = segmentClass;
		}

        
    }

    @Entity
    @Table(name = "included_checked_bags")
    public static class IncludedCheckedBags {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "weight")
        private int weight;

        @Column(name = "weight_unit")
        private String weightUnit;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public String getWeightUnit() {
			return weightUnit;
		}

		public void setWeightUnit(String weightUnit) {
			this.weightUnit = weightUnit;
		}

       
    }

    @Entity
    @Table(name = "search_price")
    public static class SearchPrice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "currency")
        private String currency;

        @Column(name = "total")
        private String total;

        @Column(name = "base")
        private String base;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "fee_id")
        private List<Fee> fees;

        @Column(name = "grand_total")
        private double grandTotal;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

		public String getBase() {
			return base;
		}

		public void setBase(String base) {
			this.base = base;
		}

		public List<Fee> getFees() {
			return fees;
		}

		public void setFees(List<Fee> fees) {
			this.fees = fees;
		}

		public double getGrandTotal() {
			return grandTotal;
		}

		public void setGrandTotal(double grandTotal) {
			this.grandTotal = grandTotal;
		}

        
    }

    @Entity
    @Table(name = "fee")
    public static class Fee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "amount")
        private double amount;

        @Column(name = "type")
        private String type;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public boolean isInstantTicketingRequired() {
		return instantTicketingRequired;
	}

	public void setInstantTicketingRequired(boolean instantTicketingRequired) {
		this.instantTicketingRequired = instantTicketingRequired;
	}

	public boolean isNonHomogeneous() {
		return nonHomogeneous;
	}

	public void setNonHomogeneous(boolean nonHomogeneous) {
		this.nonHomogeneous = nonHomogeneous;
	}

	public boolean isOneWay() {
		return oneWay;
	}

	public void setOneWay(boolean oneWay) {
		this.oneWay = oneWay;
	}

	public String getLastTicketingDate() {
		return lastTicketingDate;
	}

	public void setLastTicketingDate(String lastTicketingDate) {
		this.lastTicketingDate = lastTicketingDate;
	}

	public int getNumberOfBookableSeats() {
		return numberOfBookableSeats;
	}

	public void setNumberOfBookableSeats(int numberOfBookableSeats) {
		this.numberOfBookableSeats = numberOfBookableSeats;
	}

	public String getChoiceProbability() {
		return choiceProbability;
	}

	public void setChoiceProbability(String choiceProbability) {
		this.choiceProbability = choiceProbability;
	}

	public List<ItineraryDetails> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<ItineraryDetails> itineraries) {
		this.itineraries = itineraries;
	}

	public boolean isIncludedCheckedBagsOnly() {
		return includedCheckedBagsOnly;
	}

	public void setIncludedCheckedBagsOnly(boolean includedCheckedBagsOnly) {
		this.includedCheckedBagsOnly = includedCheckedBagsOnly;
	}

	public String[] getFareType() {
		return fareType;
	}

	public void setFareType(String[] fareType) {
		this.fareType = fareType;
	}

	public String[] getCorporateCodes() {
		return corporateCodes;
	}

	public void setCorporateCodes(String[] corporateCodes) {
		this.corporateCodes = corporateCodes;
	}

	public boolean isRefundableFare() {
		return refundableFare;
	}

	public void setRefundableFare(boolean refundableFare) {
		this.refundableFare = refundableFare;
	}

	public boolean isNoRestrictionFare() {
		return noRestrictionFare;
	}

	public void setNoRestrictionFare(boolean noRestrictionFare) {
		this.noRestrictionFare = noRestrictionFare;
	}

	public boolean isNoPenaltyFare() {
		return noPenaltyFare;
	}

	public void setNoPenaltyFare(boolean noPenaltyFare) {
		this.noPenaltyFare = noPenaltyFare;
	}

	public List<TravelerPricing> getTravelerPricings() {
		return travelerPricings;
	}

	public void setTravelerPricings(List<TravelerPricing> travelerPricings) {
		this.travelerPricings = travelerPricings;
	}

	public static SearchPrice convertSearchPrice(FlightOfferSearch.SearchPrice searchPrice) {
        SearchPrice convertedPrice = new SearchPrice();
        convertedPrice.setCurrency(searchPrice.getCurrency());
        convertedPrice.setTotal(searchPrice.getTotal());
        convertedPrice.setBase(searchPrice.getBase());
        // Convert other fields as needed
        return convertedPrice;
    }
}

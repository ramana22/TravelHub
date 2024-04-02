package com.travel.model;

import java.util.List;
import java.util.Objects;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOfferSearch.BaggageAllowance;

public class FlightBookingDetails {
    private String airline;
    private boolean instantTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
    private String choiceProbability;
    private List<ItineraryDetails> itineraries;
    private boolean includedCheckedBagsOnly;
    private String[] fareType;
    private String[] corporateCodes;
    private boolean refundableFare;
    private boolean noRestrictionFare;
    private boolean noPenaltyFare;
    private List<TravelerPricing> travelerPricings;

    // Getters and Setters

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

    // Nested Classes
    public static FlightBookingDetails.SearchPrice convertSearchPrice(FlightOfferSearch.SearchPrice searchPrice) {
        FlightBookingDetails.SearchPrice convertedPrice = new FlightBookingDetails.SearchPrice();
        convertedPrice.setCurrency(searchPrice.getCurrency());
        convertedPrice.setTotal(searchPrice.getTotal());
        convertedPrice.setBase(searchPrice.getBase());
        // Convert other fields as needed
        return convertedPrice;
    }

    public static class ItineraryDetails {
        private String duration;
        private List<SegmentDetails> segments;
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

    public static class SegmentDetails {
        private String carrierCode;
        private String number;
        private String duration;
        private int numberOfStops;
        private boolean blacklistedInEU;
        private AirportInfo departureAirport;
        private AirportInfo arrivalAirport;
        private Aircraft aircraft;
        private List<Co2Emissions> co2Emissions;
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

    public static class AirportInfo {
        private String iataCode;
        private String terminal;
        private String at;
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

    public static class Aircraft {
        private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

        
    }

    public static class Co2Emissions {
        private int weight;
        private String weightUnit;
        private String cabin;
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

    public static class TravelerPricing {
        private String travelerId;
        private String fareOption;
        private String travelerType;
        private SearchPrice price;
        private List<FareDetailsBySegment> fareDetailsBySegment;
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

    public static class FareDetailsBySegment {
        private String segmentId;
        private String cabin;
        private String fareBasis;
        private String segmentClass;
        private BaggageAllowance includedCheckedBags;
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
		public BaggageAllowance getIncludedCheckedBags() {
			return includedCheckedBags;
		}
		public void setIncludedCheckedBags(BaggageAllowance baggageAllowance) {
			this.includedCheckedBags = baggageAllowance;
		}
		
        
    }

    public static class IncludedCheckedBags {
        private int weight;
        private String weightUnit;
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

    public static class SearchPrice {
        private String currency;
        private String total;
        private String base;
        private List<Fee> fees;
        private double grandTotal;
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String string) {
			this.total = string;
		}
		public String getBase() {
			return base;
		}
		public void setBase(String string) {
			this.base = string;
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

    public static class Fee {
        private double amount;
        private String type;
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
}

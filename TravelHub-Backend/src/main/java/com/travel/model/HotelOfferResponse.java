package com.travel.model;

import java.util.List;

import com.amadeus.resources.HotelOfferSearch;
import com.amadeus.resources.HotelOfferSearch.RateFamily;
public class HotelOfferResponse {
    private List<HotelOfferSearch> data;

    public static class HotelOffer {
        private String type;
        private Hotel hotel;
        private boolean available;
        private List<Offer> offers;
        private String self;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Hotel getHotel() {
			return hotel;
		}
		public void setHotel(Hotel hotel) {
			this.hotel = hotel;
		}
		public boolean isAvailable() {
			return available;
		}
		public void setAvailable(boolean available) {
			this.available = available;
		}
		public List<Offer> getOffers() {
			return offers;
		}
		public void setOffers(List<Offer> offers) {
			this.offers = offers;
		}
		public String getSelf() {
			return self;
		}
		public void setSelf(String self) {
			this.self = self;
		}
		public HotelOffer(String type, Hotel hotel, boolean available, List<Offer> offers, String self) {
			super();
			this.type = type;
			this.hotel = hotel;
			this.available = available;
			this.offers = offers;
			this.self = self;
		}
		public HotelOffer() {
			super();
			// TODO Auto-generated constructor stub
		}
		

       
    }

    public static class Hotel {
        private String type;
        private String hotelId;
        private String chainCode;
        private String dupeId;
        private String name;
        private String cityCode;
        private double latitude;
        private double longitude;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getHotelId() {
			return hotelId;
		}
		public void setHotelId(String hotelId) {
			this.hotelId = hotelId;
		}
		public String getChainCode() {
			return chainCode;
		}
		public void setChainCode(String chainCode) {
			this.chainCode = chainCode;
		}
		public String getDupeId() {
			return dupeId;
		}
		public void setDupeId(String dupeId) {
			this.dupeId = dupeId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public double getLatitude() {
			return latitude;
		}
		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
		public double getLongitude() {
			return longitude;
		}
		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		public Hotel(String type, String hotelId, String chainCode, String dupeId, String name, String cityCode,
				double latitude, double longitude) {
			super();
			this.type = type;
			this.hotelId = hotelId;
			this.chainCode = chainCode;
			this.dupeId = dupeId;
			this.name = name;
			this.cityCode = cityCode;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		public Hotel() {
			super();
			// TODO Auto-generated constructor stub
		}
        
        
    }

    public static class Offer {
        private String id;
        private String checkInDate;
        private String checkOutDate;
        private String rateCode;
        private RateFamilyEstimated rateFamilyEstimated;
        private Room room;
        private Guests guests;
        private Price price;
        private Policies policies;
        private String self;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCheckInDate() {
			return checkInDate;
		}
		public void setCheckInDate(String checkInDate) {
			this.checkInDate = checkInDate;
		}
		public String getCheckOutDate() {
			return checkOutDate;
		}
		public void setCheckOutDate(String checkOutDate) {
			this.checkOutDate = checkOutDate;
		}
		public String getRateCode() {
			return rateCode;
		}
		public void setRateCode(String rateCode) {
			this.rateCode = rateCode;
		}
		public RateFamilyEstimated getRateFamilyEstimated() {
			return rateFamilyEstimated;
		}
		public void setRateFamilyEstimated(RateFamilyEstimated rateFamilyEstimated2) {
			this.rateFamilyEstimated = rateFamilyEstimated2;
		}
		public Room getRoom() {
			return room;
		}
		public void setRoom(Room room) {
			this.room = room;
		}
		public Guests getGuests() {
			return guests;
		}
		public void setGuests(Guests guests) {
			this.guests = guests;
		}
		public Price getPrice() {
			return price;
		}
		public void setPrice(Price price) {
			this.price = price;
		}
		public Policies getPolicies() {
			return policies;
		}
		public void setPolicies(Policies policies) {
			this.policies = policies;
		}
		public String getSelf() {
			return self;
		}
		public void setSelf(String self) {
			this.self = self;
		}
		public Offer(String id, String checkInDate, String checkOutDate, String rateCode,
				RateFamilyEstimated rateFamilyEstimated, Room room, Guests guests, Price price, Policies policies,
				String self) {
			super();
			this.id = id;
			this.checkInDate = checkInDate;
			this.checkOutDate = checkOutDate;
			this.rateCode = rateCode;
			this.rateFamilyEstimated = rateFamilyEstimated;
			this.room = room;
			this.guests = guests;
			this.price = price;
			this.policies = policies;
			this.self = self;
		}
		public Offer() {
			super();
			// TODO Auto-generated constructor stub
		}

        
    }

    public static class RateFamilyEstimated {
        private String code;
        private String type;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public RateFamilyEstimated(String code, String type) {
			super();
			this.code = code;
			this.type = type;
		}
		public RateFamilyEstimated() {
			super();
			// TODO Auto-generated constructor stub
		}

        
    }

    public static class Room {
        private String type;
        private TypeEstimated typeEstimated;
        private Description description;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public TypeEstimated getTypeEstimated() {
			return typeEstimated;
		}
		public void setTypeEstimated(TypeEstimated typeEstimated) {
			this.typeEstimated = typeEstimated;
		}
		public Description getDescription() {
			return description;
		}
		public void setDescription(Description description) {
			this.description = description;
		}
		public Room(String type, TypeEstimated typeEstimated, Description description) {
			super();
			this.type = type;
			this.typeEstimated = typeEstimated;
			this.description = description;
		}
		public Room() {
			super();
			// TODO Auto-generated constructor stub
		}

        
    }

    public static class TypeEstimated {
        private String category;
        private int beds;
        private String bedType;
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public int getBeds() {
			return beds;
		}
		public void setBeds(int beds) {
			this.beds = beds;
		}
		public String getBedType() {
			return bedType;
		}
		public void setBedType(String bedType) {
			this.bedType = bedType;
		}
		public TypeEstimated(String category, int beds, String bedType) {
			super();
			this.category = category;
			this.beds = beds;
			this.bedType = bedType;
		}
		public TypeEstimated() {
			super();
			// TODO Auto-generated constructor stub
		}
        
        
    }

    public static class Description {
        private String text;
        private String lang;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getLang() {
			return lang;
		}
		public void setLang(String lang) {
			this.lang = lang;
		}

        
    }

    public static class Guests {
        private int adults;

		public int getAdults() {
			return adults;
		}

		public void setAdults(int adults) {
			this.adults = adults;
		}

		public Guests(int adults) {
			super();
			this.adults = adults;
		}

		public Guests() {
			super();
			// TODO Auto-generated constructor stub
		}

        
    }

    public static class Price {
        private String currency;
        private String base;
        private String total;
        private Variations variations;
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getBase() {
			return base;
		}
		public void setBase(String base) {
			this.base = base;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
		public Variations getVariations() {
			return variations;
		}
		public void setVariations(Variations variations) {
			this.variations = variations;
		}
		public Price(String currency, String base, String total, Variations variations) {
			super();
			this.currency = currency;
			this.base = base;
			this.total = total;
			this.variations = variations;
		}
		public Price() {
			super();
			// TODO Auto-generated constructor stub
		}

        
    }

    public static class Variations {
        private Average average;
        private List<Change> changes;
		public Average getAverage() {
			return average;
		}
		public void setAverage(Average average) {
			this.average = average;
		}
		public List<Change> getChanges() {
			return changes;
		}
		public void setChanges(List<Change> changes) {
			this.changes = changes;
		}
		public Variations(Average average, List<Change> changes) {
			super();
			this.average = average;
			this.changes = changes;
		}
		public Variations() {
			super();
			// TODO Auto-generated constructor stub
		}
		
        
    }

    public static class Average {
        private String base;

		public String getBase() {
			return base;
		}

		public void setBase(String base) {
			this.base = base;
		}

		public Average(String base) {
			super();
			this.base = base;
		}

		public Average() {
			super();
			// TODO Auto-generated constructor stub
		}

		
        
    }

    public static class Change {
        private String startDate;
        private String endDate;
        private String base;
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public String getBase() {
			return base;
		}
		public void setBase(String base) {
			this.base = base;
		}
		public Change(String startDate, String endDate, String base) {
			super();
			this.startDate = startDate;
			this.endDate = endDate;
			this.base = base;
		}
		public Change() {
			super();
			// TODO Auto-generated constructor stub
		}
		

        
    }

    public static class Policies {
        private List<Cancellation> cancellations;
        private String paymentType;
		public List<Cancellation> getCancellations() {
			return cancellations;
		}
		public void setCancellations(List<Cancellation> cancellations) {
			this.cancellations = cancellations;
		}
		public String getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
		public Policies(List<Cancellation> cancellations, String paymentType) {
			super();
			this.cancellations = cancellations;
			this.paymentType = paymentType;
		}
		public Policies() {
			super();
			// TODO Auto-generated constructor stub
		}
		
        
    }

    public static class Cancellation {
        private Description description;
        private String type;
		public Description getDescription() {
			return description;
		}
		public void setDescription(Description description) {
			this.description = description;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Cancellation(Description description, String type) {
			super();
			this.description = description;
			this.type = type;
		}
		public Cancellation() {
			super();
			// TODO Auto-generated constructor stub
		}
		

        
    }

	

	public List<HotelOfferSearch> getData() {
		return data;
	}



	public void setData(List<HotelOfferSearch> hotelOfferSearchList) {
		this.data = hotelOfferSearchList;
	}



	public HotelOfferResponse(List<HotelOfferSearch> data) {
		super();
		this.data = data;
	}



	public HotelOfferResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}


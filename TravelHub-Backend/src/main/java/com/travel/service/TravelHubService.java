package com.travel.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.travel.model.*;

import com.travel.repository.*;


@Service
public class TravelHubService {
	@Autowired
	UserRepository userrepo;
	@Autowired
	ReviewRepository reviewrepo;
	@Autowired
	TrainRepository trainrepo;
	@Autowired
	BusRepository busrepo;
	@Autowired
	CarRepository carrepo;
	@Autowired
	ProfileRepository profilerepo;
	@Autowired
	FlightsRepository flightrepo;
	@Autowired
    Locationrepository locationrepo;
	@Autowired
	BusTicketRepository busticket;
	@Autowired
	TrainTicketRepository trainticket;
	@Autowired
	flightticketrepository flightticket;
	@Autowired
	carbookingrepository carbooking;
	@Autowired
	RestaurantRepository restrepo;
	@Autowired
	hotelbookingrepository hotelbooking;
	
	public User saveUser(User user) throws Exception {
		if(user==null) {
			throw new Exception("user is null");
		}
		return userrepo.save(user);
	}
	public User fetchemail(String email) {
		return userrepo.findByEmail(email);
	}
	public profile fetchprofile(String email) {
		return profilerepo.findByEmail(email);
	}
	public  User fetchuseremailandpassword(String email,String password) {
		return userrepo.findByEmailAndPassword(email, password);
	}
	 public Review saveReview(Review review) {
		 return reviewrepo.save(review);
	 }
	 public List<Review> getReview(){
		 return reviewrepo.findAll();
	 }
	 public Train saveTrain(Train train) {
		 return trainrepo.save(train);
	 }
	 public List<Train> searchtrains(String departureStation, String arrivalStation, LocalDate departureDate) {
	        return trainrepo.findByDepartureStationStartingWithIgnoreCaseAndArrivalStationStartingWithIgnoreCaseAndDepartureDate(departureStation, arrivalStation, departureDate);
	 }
	 public Bus saveBus(Bus bus) {
		 return busrepo.save(bus);
	 }
	 public List<Bus> searchBuses(String departureTerminal, String arrivalTerminal, LocalDate departureDate) {
	        return busrepo.findByDepartureTerminalStartingWithIgnoreCaseAndArrivalTerminalStartingWithIgnoreCaseAndDepartureDate(departureTerminal, arrivalTerminal, departureDate);
	 }
	 public Car saveCar(Car car) {
		 return carrepo.save(car);
	 }
	 public List<Car> searchCars(String pickupLocation, LocalDate rentalStartDate, LocalDate rentalEndDate) {
		    List<Car> cars = carrepo.findByPickupLocationStartingWithIgnoreCaseAndRentalStartDateLessThanEqualAndRentalEndDateGreaterThanEqual(
		            pickupLocation, rentalStartDate, rentalEndDate);
		    return cars;
	}
	@Autowired
	private RoomRepository roomrepo;
	@Autowired
	private HotelRepository hotelrepo;
	public Hotel saveHotel(Hotel hotel) {
		return hotelrepo.save(hotel);
	}
	public Room saveRoom(Room room) {
		return roomrepo.save(room);
	}
	public profile saveprofile(profile profile) {
		return profilerepo.save(profile);
	}
	public List<Hotel> getAllHotels() {
	    return hotelrepo.findAll();
	}

	public List<LocationData> saveAll(List<LocationData> locationDataList) {
	    return locationrepo.saveAll(locationDataList);	
	}
	public List<FlightBookingDetails> saveAllflights(List<FlightBookingDetails> FlightBookingDetails) {
	    return flightrepo.saveAll(FlightBookingDetails);	
	}
	public BusTicket savebusticket(BusTicket busticketobject) {
		return busticket.save(busticketobject);
	}
	public TrainTicket savetrainticket(TrainTicket trainticketobject) {
		return trainticket.save(trainticketobject);
	}
	public FlightTicket saveflightticket(FlightTicket flightticketobject) {
		return flightticket.save(flightticketobject);
	}
	public CarBooking savecarbooking(CarBooking carookingobject) {
		return carbooking.save(carookingobject);
	}
	public HotelBooking savehotelbooking(HotelBooking hotelbookingobject) {
		return hotelbooking.save(hotelbookingobject);
	}
	public Restaurant saveRestaurant(Restaurant restaurant) {
		return restrepo.save(restaurant);
	}
	public List<Restaurant> getallrestaurants() {
	    return restrepo.findAll();
	}
	public List<HotelBooking> getBookingsByUserEmail(String email) {
	    return hotelbooking.findByUserEmail(email);
	}
	public List<FlightTicket> getflightByUserEmail(String email) {
	    return flightticket.findByUserEmail(email);
	}
	public List<TrainTicket> getTrainByUserEmail(String email) {
	    return trainticket.findByUserEmail(email);
	}
	public List<BusTicket> getBusByUserEmail(String email) {
	    return busticket.findByUserEmail(email);
	}
	public List<CarBooking> getCarByUserEmail(String email) {
	    return carbooking.findByUserEmail(email);
	}
	public void deleteBusTicket(String email, Long busTicketId) throws NotFoundException {
	    BusTicket busTicket = busticket.findByIdAndUserEmail(busTicketId, email);
	    if (busTicket != null) {
	        busticket.delete(busTicket);
	    } else {
	        // Handle case where bus ticket is not found
	        // For example, throw an exception or log an error
	        throw new NotFoundException();
	    }
	}
	public void deletetrainTicket(String email, Long trainticketId) throws NotFoundException {
	    TrainTicket trainTicket = trainticket.findByIdAndUserEmail(trainticketId, email);
	    if (trainTicket != null) {
	        trainticket.delete(trainTicket);
	    } else {
	        // Handle case where bus ticket is not found
	        // For example, throw an exception or log an error
	        throw new NotFoundException();
	    }
	}
	public void deletehotelbooking(String email, Long hotelbookingId) throws NotFoundException {
		HotelBooking hotelbook = hotelbooking.findByIdAndUserEmail(hotelbookingId, email);
	    if (hotelbook != null) {
	        hotelbooking.delete(hotelbook);
	    } else {
	        // Handle case where bus ticket is not found
	        // For example, throw an exception or log an error
	        throw new NotFoundException();
	    }
	}
	public void deletecarbooking(String email, Long carbookingId) throws NotFoundException {
	    CarBooking carbook = carbooking.findByIdAndUserEmail(carbookingId, email);
	    if (carbook != null) {
	        carbooking.delete(carbook);
	    } else {
	        // Handle case where bus ticket is not found
	        // For example, throw an exception or log an error
	        throw new NotFoundException();
	    }
	}
	public void deleteflightTicket(String email, Long flightticketId) throws NotFoundException {
	    FlightTicket flightTicket = flightticket.findByIdAndUserEmail(flightticketId, email);
	    if (flightTicket != null) {
	        flightticket.delete(flightTicket);
	    } else {
	        // Handle case where bus ticket is not found
	        // For example, throw an exception or log an error
	        throw new NotFoundException();
	    }
	}
    
}

package com.travel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Bus;
import com.travel.model.Review;
import com.travel.model.Train;
import com.travel.model.User;
import com.travel.repository.BusRepository;
import com.travel.repository.ReviewRepository;
import com.travel.repository.TrainRepository;
import com.travel.repository.UserRepository;

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
	public User saveUser(User user) throws Exception {
		if(user==null) {
			throw new Exception("user is null");
		}
		return userrepo.save(user);
	}
	public User fetchemail(String email) {
		return userrepo.findByEmail(email);
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
	 
}

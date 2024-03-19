package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.User;
import com.travel.repository.UserRepository;

@Service
public class TravelHubService {
	@Autowired
	UserRepository userrepo;
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
}

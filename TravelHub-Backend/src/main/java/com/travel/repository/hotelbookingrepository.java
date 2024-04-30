package com.travel.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.HotelBooking;

public interface hotelbookingrepository  extends JpaRepository< HotelBooking,Long>{
	List<HotelBooking> findByUserEmail(String email);
	HotelBooking findByIdAndUserEmail(Long id, String userEmail);
}

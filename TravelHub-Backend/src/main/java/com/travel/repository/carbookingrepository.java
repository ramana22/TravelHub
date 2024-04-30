package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.CarBooking;

public interface carbookingrepository extends JpaRepository<CarBooking,Long>{
	List<CarBooking> findByUserEmail(String email);
	CarBooking findByIdAndUserEmail(Long id, String userEmail);
}

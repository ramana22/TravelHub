package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.FlightTicket;

public interface flightticketrepository extends JpaRepository<FlightTicket,Long>{
	List<FlightTicket> findByUserEmail(String email);
	FlightTicket findByIdAndUserEmail(Long id, String userEmail);
}

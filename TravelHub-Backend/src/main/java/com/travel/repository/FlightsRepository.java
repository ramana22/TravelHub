package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.FlightBookingDetails;

public interface FlightsRepository extends JpaRepository<FlightBookingDetails,Long>{

}

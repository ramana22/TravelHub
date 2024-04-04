package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.FlightTicket;

public interface flightticketrepository extends JpaRepository<FlightTicket,Long>{

}

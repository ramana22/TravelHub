package com.travel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Bus;

public interface BusRepository extends JpaRepository<Bus,Long>{

	List<Bus> findByDepartureTerminalStartingWithIgnoreCaseAndArrivalTerminalStartingWithIgnoreCaseAndDepartureDate(
			String departureTerminal, String arrivalTerminal, LocalDate departureDate);

}

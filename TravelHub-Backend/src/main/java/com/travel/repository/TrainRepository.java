package com.travel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.model.Train;

public interface TrainRepository extends JpaRepository<Train,Long>{

	List<Train> findByDepartureStationStartingWithIgnoreCaseAndArrivalStationStartingWithIgnoreCaseAndDepartureDate(
			String departureStation, String arrivalStation, LocalDate departureDate);

}

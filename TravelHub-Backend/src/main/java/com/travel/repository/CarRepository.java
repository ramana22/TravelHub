package com.travel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Car;


public interface CarRepository extends JpaRepository<Car,Long>{

	List<Car> findByPickupLocationStartingWithIgnoreCaseAndRentalStartDateLessThanEqualAndRentalEndDateGreaterThanEqual(
		    String pickupLocation, LocalDate rentalStartDate, LocalDate rentalEndDate);
}

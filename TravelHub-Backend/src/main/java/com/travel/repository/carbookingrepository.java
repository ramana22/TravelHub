package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.CarBooking;

public interface carbookingrepository extends JpaRepository<CarBooking,Long>{

}

package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.HotelBooking;

public interface hotelbookingrepository  extends JpaRepository< HotelBooking,Long>{

}

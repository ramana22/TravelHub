package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Traveler;

public interface travelerrepository extends JpaRepository<Traveler,Long>{

}

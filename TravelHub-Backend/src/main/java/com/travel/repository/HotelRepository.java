package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long>{

}

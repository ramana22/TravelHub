package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.LocationData;

public interface Locationrepository extends JpaRepository<LocationData,Long>{

}

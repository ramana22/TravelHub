package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Restaurant;


public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{
	public Restaurant findByRestaurantid(Long restaurantid);
}

package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
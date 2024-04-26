package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.travel.model.profile;

public interface ProfileRepository extends JpaRepository<profile,Long>{
	public profile findByEmail(String email);
}
package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Notify;

public interface NotifyRepository extends JpaRepository<Notify,Long>{
	List<Notify> findByEmail(String email);
}



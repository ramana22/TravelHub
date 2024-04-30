package com.travel.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.BusTicket;


public interface BusTicketRepository extends JpaRepository<BusTicket,Long> {
	List<BusTicket> findByUserEmail(String email);
	BusTicket findByIdAndUserEmail(Long id, String userEmail);
}

package com.travel.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.TrainTicket;

public interface TrainTicketRepository  extends JpaRepository<TrainTicket,Long> {
	List<TrainTicket> findByUserEmail(String email);
	TrainTicket findByIdAndUserEmail(Long id, String userEmail);
}

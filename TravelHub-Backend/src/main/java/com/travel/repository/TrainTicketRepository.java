package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.TrainTicket;

public interface TrainTicketRepository  extends JpaRepository<TrainTicket,Long> {

}

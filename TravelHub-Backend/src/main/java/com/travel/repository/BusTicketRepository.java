package com.travel.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.BusTicket;



public interface BusTicketRepository extends JpaRepository<BusTicket,Long> {

}

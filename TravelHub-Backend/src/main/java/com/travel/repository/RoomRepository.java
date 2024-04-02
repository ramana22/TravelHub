package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {

}

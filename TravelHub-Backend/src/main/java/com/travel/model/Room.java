package com.travel.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Room_id;

    private String roomNumber;
    private int capacity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private Bill bill;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;
	public Long getRoom_id() {
		return Room_id;
	}
	public void setRoom_id(Long room_id) {
		Room_id = room_id;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Room(Long room_id, String roomNumber, int capacity, Bill bill, Image image) {
		super();
		Room_id = room_id;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.bill = bill;
		this.image = image;
	}
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	} 
}

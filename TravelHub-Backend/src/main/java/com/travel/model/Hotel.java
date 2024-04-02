package com.travel.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelid;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private float rating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Room_id")
    private Room room;
	
	public Long getHotelid() {
		return hotelid;
	}
	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Hotel(Long hotelid, String name, float rating, Address address, Image image, Room room) {
		super();
		this.hotelid = hotelid;
		this.name = name;
		this.rating = rating;
		this.address = address;
		this.image = image;
		this.room = room;
	}
	
	@Override
	public String toString() {
		return "Hotel [hotelid=" + hotelid + ", name=" + name + ", rating=" + rating + ", address=" + address
				+ ", image=" + image + ", room=" + room + "]";
	}
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}

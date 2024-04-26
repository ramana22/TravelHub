package com.travel.model;


import jakarta.persistence.*;

@Entity
@Table(name = "hotel_bookings")
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Additional fields for hotel details, if needed

    @OneToOne
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "HotelBooking [id=" + id + ", traveler=" + traveler + ", payment=" + payment + ", user=" + user
				+ ", hotel=" + hotel + "]";
	}
}

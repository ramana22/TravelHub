package com.travel.model;


import jakarta.persistence.*;

@Entity
@Table(name = "hotel_bookings")
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "checkin_date")
    private String checkinDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;


    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    @ManyToOne // Changed to ManyToOne
    @JoinColumn(name = "user_id") // Changed to ManyToOne
    private User user;
    
    @ManyToOne
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    @Override
    public String toString() {
        return "HotelBooking [id=" + id + ", checkinDate=" + checkinDate + ", traveler=" + traveler + ", payment="
                + payment + ", user=" + user + ", hotel=" + hotel + "]";
    }
}

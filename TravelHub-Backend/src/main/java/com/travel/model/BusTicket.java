package com.travel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_tickets")
public class BusTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    @ManyToOne // Changed to ManyToOne
    @JoinColumn(name = "user_id") // Changed to ManyToOne
    private User user;
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
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
    
}

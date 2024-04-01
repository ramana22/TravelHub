package com.travel.model;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    private Long busNumber;
    private String operator;
    private String departureTerminal;
    private String arrivalTerminal;

    @Column(name = "departure_Date")
    private LocalDate departureDate;

    @Column(name = "departure_Time")
    private LocalTime departureTime;

    @Column(name = "arrival_Date")
    private LocalDate arrivalDate;

    @Column(name = "arrival_Time")
    private LocalTime arrivalTime;

    private double price;

    

    public Duration getDuration() {
        if (departureTime != null && arrivalTime != null && departureDate != null && arrivalDate != null) {
            LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
            LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);
            return Duration.between(departureDateTime, arrivalDateTime);
        } else {
            return null; // Handle case where either departure or arrival time is null
        }
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public Long getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(Long busNumber) {
        this.busNumber = busNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	public Bus(Long busId, Long busNumber, String operator, String departureTerminal, String arrivalTerminal,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			double price) {
		super();
		this.busId = busId;
		this.busNumber = busNumber;
		this.operator = operator;
		this.departureTerminal = departureTerminal;
		this.arrivalTerminal = arrivalTerminal;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.price = price;
	}

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}
}
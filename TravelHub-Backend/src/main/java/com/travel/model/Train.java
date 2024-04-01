package com.travel.model;

import java.time.Duration;
import java.time.LocalDate;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainId;

    private Long trainNumber;
    private String operator;
    private String departureStation;
    private String arrivalStation;

    @Column(name = "departure_Date")
    private LocalDate departureDate;

    @Column(name = "departure_Time")
    private LocalTime departureTime;

    @Column(name = "arrival_Date")
    private LocalDate arrivalDate;

    @Column(name = "arrival_Time")
    private LocalTime arrivalTime;

    private double price;
    public Train() {
		super();
	}

	public Train(Long trainId, Long trainNumber, String operator, String departureStation, String arrivalStation,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			double price) {
		super();
		this.trainId = trainId;
		this.trainNumber = trainNumber;
		this.operator = operator;
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.price = price;
	}

	public Long getTrainId() {
		return trainId;
	}

	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}

	public Long getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(Long trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}

	public String getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(String arrivalStation) {
		this.arrivalStation = arrivalStation;
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

	public Duration getDuration() {
        if (departureTime != null && arrivalTime != null) {
            return Duration.between(departureTime, arrivalTime);
        } else {
            return null; // Handle case where either departure or arrival time is null
        }
    }
}

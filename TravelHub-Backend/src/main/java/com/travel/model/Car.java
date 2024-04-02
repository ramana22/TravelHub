package com.travel.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carModel;
    private String providerName;
    private String providerPhoneNumber;
    private String providerEmail;
    private String providerAddress;
    private String pickupLocation;
    private String dropOffLocation;
    private Date rentalStartDate;
    private Date rentalEndDate;
    private double totalPrice;
    private String currency;
    private String carImageUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderPhoneNumber() {
		return providerPhoneNumber;
	}
	public void setProviderPhoneNumber(String providerPhoneNumber) {
		this.providerPhoneNumber = providerPhoneNumber;
	}
	public String getProviderEmail() {
		return providerEmail;
	}
	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}
	public String getProviderAddress() {
		return providerAddress;
	}
	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public String getDropOffLocation() {
		return dropOffLocation;
	}
	public void setDropOffLocation(String dropOffLocation) {
		this.dropOffLocation = dropOffLocation;
	}
	public Date getRentalStartDate() {
		return rentalStartDate;
	}
	public void setRentalStartDate(Date rentalStartDate) {
		this.rentalStartDate = rentalStartDate;
	}
	public Date getRentalEndDate() {
		return rentalEndDate;
	}
	public void setRentalEndDate(Date rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCarImageUrl() {
		return carImageUrl;
	}
	public void setCarImageUrl(String carImageUrl) {
		this.carImageUrl = carImageUrl;
	}
	public Car(Long id, String carModel, String providerName, String providerPhoneNumber, String providerEmail,
			String providerAddress, String pickupLocation, String dropOffLocation, java.sql.Date rentalStartDate,
			java.sql.Date rentalEndDate, double totalPrice, String currency, String carImageUrl) {
		super();
		this.id = id;
		this.carModel = carModel;
		this.providerName = providerName;
		this.providerPhoneNumber = providerPhoneNumber;
		this.providerEmail = providerEmail;
		this.providerAddress = providerAddress;
		this.pickupLocation = pickupLocation;
		this.dropOffLocation = dropOffLocation;
		this.rentalStartDate = rentalStartDate;
		this.rentalEndDate = rentalEndDate;
		this.totalPrice = totalPrice;
		this.currency = currency;
		this.carImageUrl = carImageUrl;
	}
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}

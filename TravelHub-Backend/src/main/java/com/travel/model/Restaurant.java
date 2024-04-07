package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantid;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private float rating;
    @Column(nullable=false)
    private String review;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Bill_id")
    private Bill Bill;
	
	public Long getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(Long restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getName() {
		return name;
	}
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public void setName(String name) {
		this.name = name;
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
	public Bill getBill() {
		return Bill;
	}
	public void setBill(Bill bill) {
		Bill = bill;
	}

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
}













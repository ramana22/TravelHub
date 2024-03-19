package com.travel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewid;
	private float rating;
	private String comment;
	private String reviewemail;
	private String date;
	
	@OneToOne
    @JoinColumn(name = "userid")
    private User userDetails;
    
	

	public String getReviewemail() {
		return reviewemail;
	}

	public void setReviewemail(String reviewemail) {
		this.reviewemail = reviewemail;
	}

	public Long getReviewid() {
		return reviewid;
	}

	public void setReviewid(Long reviewid) {
		this.reviewid = reviewid;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}
	

	public Review(Long reviewid, float rating, String comment, String reviewemail, String date, User userDetails) {
		super();
		this.reviewid = reviewid;
		this.rating = rating;
		this.comment = comment;
		this.reviewemail = reviewemail;
		this.date = date;
		this.userDetails = userDetails;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

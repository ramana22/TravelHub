package com.travel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "notify")
public class Notify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Lob
    private String message;
    
	@Column(nullable = false)
	private String email;

    public Notify(String message) {
        this.message = message;
    }

    // Getter and setter for the message property
    public String getMessage() {
        return message;
    }
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMessage(String message) {
        this.message = message;
    }

	public Notify() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notify(Long id, String message, String email) {
		super();
		this.id = id;
		this.message = message;
		this.email = email;
	}

	
    
}

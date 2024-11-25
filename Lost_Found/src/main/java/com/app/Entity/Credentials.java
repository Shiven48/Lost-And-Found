package com.app.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Credentials 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	    
	@Column(nullable = false, unique = true)
	@Email(message = "Please provide a valid email address")
	@NotBlank(message = "Email is required")
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "Password is required")
	private String password;
	    
	@OneToOne(mappedBy = "credentials")
	@JsonBackReference
	private User user;

	public Credentials() {}

	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return "Credentials [id=" + id + ", email=" + email + "]";
	}

}

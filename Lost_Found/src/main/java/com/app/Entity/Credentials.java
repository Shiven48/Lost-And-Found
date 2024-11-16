package com.app.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Credentials 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long credentialsId;
	    
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	    
	@Column(nullable = false)
	private String password;
	    
	@OneToOne(mappedBy = "credentials")
	@JsonBackReference
	private User user;
	    
	public Credentials() {}
	
	public Credentials(Long credentialsId, 
					   @NotBlank @Email String email,
					   @NotBlank String password, User user) 
	{
		super();
		this.credentialsId = credentialsId;
		this.email = email;
		this.password = password;
		this.user = user;
	}

	public Long getCredentialsId() {
		return credentialsId;
	}

	public void setCredentialsId(Long credentialsId) {
		this.credentialsId = credentialsId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "Credentials [credentialsId=" + credentialsId + ", Email=" + email + ", Password=" + password + ", user="
				+ user + "]";
	}
	
}

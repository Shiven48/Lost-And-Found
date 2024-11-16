package com.app.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long User_Id;
    
    @NotBlank
    @Size(max = 25, message = "The name must be less than 25 characters")
    @Column(nullable = false, updatable = true)
    protected String Name;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected LocalDateTime Registration_Date;
    
    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    protected LocalDateTime lastModified;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "credentials_id")
    @JsonManagedReference
    private Credentials credentials;

	public User() {}
	
	public User(
			Long user_Id,
			@NotBlank @Size(max = 25, message = "The name must be less than 25 characters") String name,
			@NotBlank @Email String email, 
			@NotBlank String password,
			LocalDateTime registration_Date, 
			LocalDateTime lastModified,
			Credentials credientials) 
	{
		super();
		User_Id = user_Id;
		Name = name;
		this.credentials = credientials;
		Registration_Date = registration_Date;
		this.lastModified = lastModified;
	}

	public Long getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(Long user_Id) {
		User_Id = user_Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public LocalDateTime getRegistration_Date() {
		return Registration_Date;
	}

	public void setRegistration_Date(LocalDateTime registration_Date) {
		Registration_Date = registration_Date;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "User [User_Id=" + User_Id + ", Name=" + Name + ", Registration_Date=" + Registration_Date
				+ ", lastModified=" + lastModified + ", credentials=" + credentials + "]";
	}

}

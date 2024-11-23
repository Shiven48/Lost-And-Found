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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long userid;
    
    @NotBlank
    @Size(max = 25, message = "The name must be less than 25 characters")
    @Column(nullable = false, updatable = true)
    protected String name;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected LocalDateTime registrationdate;
    
    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    protected LocalDateTime lastmodified;
    
    @Column(nullable = false, updatable = true)
    protected boolean isloggedin;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "credentials_id")
    @JsonManagedReference
    private Credentials credentials;

	public User() {}
	
	public User(
			Long userid,
			@NotBlank @Size(max = 25, message = "The name must be less than 25 characters") String name,
			LocalDateTime registrationdate,
			LocalDateTime lastmodified,
			Credentials credentialsid,
			Boolean isloggedin)
	{
		this.userid = userid;
		this.name = name;
		this.credentials = credentialsid;
		this.registrationdate = registrationdate;
		this.lastmodified = lastmodified;
		this.isloggedin = isloggedin;
	}

	public Long getUser_Id() {
		return userid;
	}

	public void setUser_Id(Long user_Id) {
		this.userid = user_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public LocalDateTime getRegistration_Date() {
		return registrationdate;
	}

	public void setRegistration_Date(LocalDateTime registrationdate) {
		registrationdate = registrationdate;
	}

	public LocalDateTime getLastModified() {
		return lastmodified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastmodified = lastModified;
	}
	
	public boolean isLoggedIn() {
		return isloggedin;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isloggedin = isLoggedIn;
	}

	@Override
	public String toString() {
		return "User [User_Id=" + userid + ", Name=" + name + ", Registration_Date=" + registrationdate
				+ ", lastModified=" + lastmodified + ", credentials=" + credentials + "]";
	}
}

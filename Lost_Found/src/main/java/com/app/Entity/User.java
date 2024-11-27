package com.app.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@NotBlank
	@Size(max = 25, message = "Name must be less than 25 characters")
	@Column(nullable = false)
	private String name;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime registrationDate;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime lastModified;

	@JsonProperty("loggedIn")
	@Column(nullable = false)
	private Boolean isLoggedIn;

	@OneToMany(mappedBy = "finder",cascade = CascadeType.ALL)
	private List<Item> itemsFound = new ArrayList<Item>();

	@OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
	private List<Item> itemsLost = new ArrayList<Item>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(
			name = "credential_id",
			referencedColumnName = "id",
			unique = true,
			nullable = false
	)
	@JsonManagedReference
	private Credentials credentials;

	public User() {}

	public User(String name,Boolean isLoggedIn,Credentials credentials) {
		this.name = name;
		this.isLoggedIn = isLoggedIn;
		this.credentials = credentials;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void addFoundItem(Item item) {
		itemsFound.add(item);
		item.setFinder(this);
	}

	public void addLostItem(Item item) {
		itemsLost.add(item);
		item.setOwner(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public Boolean getLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.isLoggedIn = loggedIn;
	}

	public List<Item> getItemsFound() {
		return itemsFound;
	}

	public void setItemsFound(List<Item> itemsFound) {
		this.itemsFound = itemsFound;
	}

	public List<Item> getItemsLost() {
		return itemsLost;
	}

	public void setItemsLost(List<Item> itemsLost) {
		this.itemsLost = itemsLost;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		return "User [id:"+userId+"Name : "+name+","+"isLoggedIn : "+isLoggedIn+","+"credentials : "+credentials+"]";
	}
}
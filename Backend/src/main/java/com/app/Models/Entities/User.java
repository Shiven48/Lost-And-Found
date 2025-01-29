package com.app.Models.Entities;

import java.util.ArrayList;
import java.util.List;

import com.app.Models.Common.BaseAudit;
import com.app.Models.Enums.Lost_Found;
import com.app.Models.Interface.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER_TABLE")
public class User extends BaseAudit implements UserType
{
	@JsonProperty("loggedIn")
	@Column(nullable = false)
	private Boolean isLoggedIn;

	@Column
	private Lost_Found lost_found;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Credentials_id", referencedColumnName = "id")
	private Credentials credentials;

	@OneToMany(mappedBy = "finder",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Item> itemsFound = new ArrayList<>();

	@OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Item> itemsLost = new ArrayList<>();

	public User() {}

	public User(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public Long getId(){
		return super.id;
	}

	public void setId(Long id){
		super.id = id;
	}

	// For founder specific route
	public void addFoundItem(Item item) {
		itemsFound.add(item);
		item.setFinder(this);
	}

	// For Owner specific route
	public void addLostItem(Item item) {
		itemsLost.add(item);
		item.setOwner(this);
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

	public Lost_Found getLost_found() {
		return lost_found;
	}

	public void setLost_found(Lost_Found lost_found) {
		this.lost_found = lost_found;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		return "User{" +
				"isLoggedIn=" + isLoggedIn +
				", lost_found=" + lost_found +
				", credentials=" + credentials +
				", itemsFound=" + itemsFound +
				", itemsLost=" + itemsLost +
				", registrationDate=" + registrationDate +
				", lastModified=" + lastModified +
				", id=" + id +
				'}';
	}
}
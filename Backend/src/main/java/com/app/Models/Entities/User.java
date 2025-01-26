package com.app.Entity.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER_TABLE")
public class User extends Credentials
{
	@Column
	private String name;

	@JsonProperty("loggedIn")
	@Column(nullable = false)
	private Boolean isLoggedIn;

	@OneToMany(mappedBy = "finder",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Item> itemsFound = new ArrayList<>();

	@OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Item> itemsLost = new ArrayList<>();

	public User() {}

	public User(String name,Boolean isLoggedIn) {
		this.name = name;
		this.isLoggedIn = isLoggedIn;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User ["+
				"Name : " + name+"," +
				"isLoggedIn : "+isLoggedIn +
				super.toString() +
				"]";
	}
}
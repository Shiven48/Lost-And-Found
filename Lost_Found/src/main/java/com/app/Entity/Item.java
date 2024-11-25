package com.app.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@Entity
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@Size(max = 20)
	@NotBlank(message = "The name should not be empty")
	private String name;
	
	@Size(max = 3000,message = "The message should not be more than 2000 words")
	private String description;
	
	private String obj_Type;
	
	private String obj_Image;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Lost_Found lost_found;

	private String place;

	private LocalTime time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "finder_id")
	private User finder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private User owner;

	public Item() {}

	public Item(String name, String description, String obj_Type,
				String obj_Image, Lost_Found lost_found, String place,
				LocalTime time, User finder, User owner) {
		this.name = name;
		this.description = description;
		this.obj_Type = obj_Type;
		this.obj_Image = obj_Image;
		this.lost_found = lost_found;
		this.place = place;
		this.time = time;
		this.finder = finder;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObj_Type() {
		return obj_Type;
	}

	public void setObj_Type(String obj_Type) {
		this.obj_Type = obj_Type;
	}

	public String getObj_Image() {
		return obj_Image;
	}

	public void setObj_Image(String obj_Image) {
		this.obj_Image = obj_Image;
	}

	public Lost_Found getLost_found() {
		return lost_found;
	}

	public void setLost_found(Lost_Found lost_found) {
		this.lost_found = lost_found;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public User getFinder() {
		return finder;
	}

	public void setFinder(User finder) {
		this.finder = finder;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", obj_Type=" + obj_Type
				+ ", obj_Image=" + obj_Image + ", lost_found=" + lost_found + ", place=" + place + ", time=" + time
				+ ", finder=" + finder + ", owner=" + owner + "]";
	}
}

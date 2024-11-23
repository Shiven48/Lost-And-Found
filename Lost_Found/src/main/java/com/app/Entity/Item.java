package com.app.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@Entity
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Size(max = 20)
	@NotBlank(message = "The name should not be empty")
	private String name;
	
	@Size(max = 3000,message = "The message should not be more than 2000 words")
	private String description;
	
	private String type;
	
	private String objimage;
	
	@ManyToOne
	@JoinColumn(name = "userfound_id")
	private UserFound userfound;
	
	@ManyToOne
	@JoinColumn(name = "userlost_id")
	private UserLost userlost;
	
	private Lost_Found lost_found;		// Object type - Lost or Found

	private String place;

	private LocalTime time;
	
	public Item() {}
	
	public Item(int id, String name, String description, String type, String objimage,String place, LocalTime time) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.objimage = objimage;
		this.place = place;
		this.time = time;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getObj_image() {
		return objimage;
	}
	public void setObj_image(String obj_image) {
		this.objimage = obj_image;
	}
	
	public Lost_Found getLost_found() {
		return lost_found;
	}
	public void setLost_found(Lost_Found lost_found) {
		this.lost_found = lost_found;
	}

	public UserFound getUserfound() {
		return userfound;
	}
	public void setUserfound(UserFound userfound) {
		this.userfound = userfound;
	}

	public UserLost getUserlost() {
		return userlost;
	}
	public void setUserlost(UserLost userlost) {
		this.userlost = userlost;
	}

	public String getPlace() {
		return this.place;
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type
				+ ", obj_image=" + objimage + ", lost_found=" + lost_found + "]";
	}
}

package com.app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Object 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(max = 20)
	@NotBlank(message = "The name should not be empty")
	private String name;
	
	@Size(max = 3000,message = "The message should not be more than 2000 words")
	private String description;
	
	private String type;
	
	private String obj_image;
	
	@ManyToOne
	@JoinColumn(name = "userfound_id")
	private UserFound userfound;
	
	@ManyToOne
	@JoinColumn(name = "userlost_id")
	private UserLost userlost;
	
	private Lost_Found lost_found;		// Object type - Lost or Found 
	
	public Object()
	{	
	}
	
	public Object(int id, String name, String description, String type, String obj_image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.obj_image = obj_image;
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getObj_image() {
		return obj_image;
	}
	public void setObj_image(String obj_image) {
		this.obj_image = obj_image;
	}
	
	public Lost_Found getLost_found() {
		return lost_found;
	}
	public void setLost_found(Lost_Found lost_found) {
		this.lost_found = lost_found;
	}

	@Override
	public String toString() {
		return "Object [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type
				+ ", obj_image=" + obj_image + ", lost_found=" + lost_found + "]";
	}
}

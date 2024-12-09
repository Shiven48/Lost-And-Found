package com.app.Entity;

import com.app.Interface.Taggable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item implements Taggable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(max = 20)
	@NotBlank(message = "The name should not be empty")
	private String name;
	
	@Size(max = 3000,message = "The message should not be more than 2000 words")
	private String description;
	
	private String category;
	
	private String obj_Image;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Lost_Found lost_found;

	private String place;

	private LocalTime time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "finder_id")
	@JsonBackReference("userFinder")
	private User finder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	@JsonBackReference("userOwner")
	private User owner;

	@ElementCollection
	@CollectionTable(name = "item_tags", joinColumns = @JoinColumn(name = "item_id"))
	@Column(name = "tag")
	private List<String> tags = new ArrayList<String>();


	public Item() {}

	public Item(String name, String description, String category,
				String obj_Image, Lost_Found lost_found, String place,
				LocalTime time, User finder, User owner) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.obj_Image = obj_Image;
		this.lost_found = lost_found;
		this.place = place;
		this.time = time;
		this.finder = finder;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
	}

	public void addTag(String tag) {
		if (tag != null && !tag.trim().isEmpty()) {
			this.tags.add(tag);
		}
	}

	public void removeTag(String tag) {
		this.tags.remove(tag);
	}

	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", obj_Type=" + category
				+ ", obj_Image=" + obj_Image + ", lost_found=" + lost_found + ", place=" + place + ", time=" + time
				+ ", finder=" + finder + ", owner=" + owner + "]";
	}
}

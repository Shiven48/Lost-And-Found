package com.app.Entity;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class UserFound extends User
{
	@Column
	private String place;
	
	@Column
	private LocalTime time;
	
	@Column(name = "tags")
	@ElementCollection
	@CollectionTable(name = "user_found_tags" , joinColumns = {@JoinColumn(name = "user_found_id")})
	private List<String> tags;
	
	@OneToMany(mappedBy = "userfound")
	private List<Object> found_object;

	public UserFound() {}
	
	public UserFound(String place, LocalTime time, List<String> tags) {
		this.place = place;
		this.time = time;
		this.tags = tags;
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public Credentials getCredentials() {
		return super.getCredentials();
	}

    @Override
	public String toString() {
		return "UserFound [user_id=" + user_id + ", name=" + name +
				", place=" + place + ", time=" + time + ", tags=" + tags + "]";
	}
}

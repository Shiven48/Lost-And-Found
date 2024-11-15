package com.app.Entity;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class UserLost extends User
{
	
	@Column
	private String place;
	
	@Column
	private LocalTime time;
	
	@Column
	private String imageid;
	
	@OneToMany
	private List<Object> lost_object;

	public UserLost() {}
	
	public UserLost(String place, LocalTime time, String imageid) {
		super();
		this.place = place;
		this.time = time;
		this.imageid = imageid;
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

	public String getImageId() {
		return imageid;
	}

	public void setImageId(String imageId) {
		imageid = imageId;
	}

	@Override
	public String toString() {
		return "UserLost [place=" + place + ", time=" + time + ", ImageId=" + imageid + "]";
	}
	
}

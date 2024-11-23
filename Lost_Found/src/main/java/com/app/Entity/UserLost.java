package com.app.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class UserLost extends User
{
	
	@Column
	private String place;
	
	@Column
	private LocalTime time;
	
	@Column
	private String imageid;
	
	@OneToMany(mappedBy = "userlost")
	private List<Item> lostobject;

	public UserLost() {}

	public UserLost(
			Long userid,
			@NotBlank @Size(max = 25, message = "The name must be less than 25 characters") String name,
			LocalDateTime registrationdate,
			LocalDateTime lastmodified,
			boolean isloggedin,
			Credentials credentials,
			String place,
			LocalTime time,
			String imageid
	) {
		super(userid, name, registrationdate, lastmodified, credentials, isloggedin);
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

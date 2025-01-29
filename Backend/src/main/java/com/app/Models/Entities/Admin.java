package com.app.Models.Entities;

import com.app.Models.Common.BaseAudit;
import com.app.Models.Interface.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ADMIN_TABLE")
public class Admin extends BaseAudit implements UserType
{
	@JsonProperty("loggedIn")
	@Column(nullable = false)
	private Boolean isLoggedIn;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Credentials_id", referencedColumnName = "id")
	private Credentials credentials;

	public Admin(){ }

	@Override
	public Credentials getCredentials() {
		return credentials;
	}

	@Override
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Boolean getLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		isLoggedIn = loggedIn;
	}

	public Long getId(){
		return super.id;
	}

	public void setId(Long id){
		super.id = id;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"isLoggedIn=" + isLoggedIn +
				", credentials=" + credentials +
				'}';
	}
}

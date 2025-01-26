package com.app.Entity.Models;

import com.app.Entity.Common.BaseAudit;
import jakarta.persistence.*;

@Entity
@Table(name = "CREDENTIALS_TABLE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credentials extends BaseAudit
{
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column
	private String roles;

	public Credentials() {}

	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String toString() {
		return "Credentials [id=" + id + ", email=" + email + "]";
	}

}

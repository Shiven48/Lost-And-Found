package com.app.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class Admin 
{
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer adminId;
	
	@Column
	@Email
	@NotNull
	private String email;
	
	@Column
	@Min(value = 8,message = "The password must be more than 8 alphabets")
	@NotNull
	private String password;

	public Admin() { }

	public Admin(String email, String password){
		this.email = email;
		this.password = password;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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

	@Override
	public String toString() {
		return "Admin{" +
				"adminId=" + adminId +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}

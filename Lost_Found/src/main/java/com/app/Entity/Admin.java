package com.app.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Admin 
{
	@Column
	@Id
	private Integer admin_id;
	
	@Column
	@Email
	@NotNull
	private String email;
	
	@Column
	@Min(value = 8,message = "The password must be more than 8 alphabets")
	@NotNull
	private String password;

	public Admin() {}
	
	public Admin(Integer adminId, @Email String adminEmail,
			@Min(value = 8, message = "The password must be more than 8 alphabets") String adminPassword) {
		super();
		admin_id = adminId;
		email = adminEmail;
		password = adminPassword;
	}

	public Integer getAdminId() {
		return admin_id;
	}

	public void setAdminId(Integer adminId) {
		this.admin_id = adminId;
	}

	public String getAdminEmail() {
		return email;
	}

	public void setAdminEmail(String adminEmail) {
		email = adminEmail;
	}

	public String getAdminPassword() {
		return password;
	}

	public void setAdminPassword(String adminPassword) {
		password = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + admin_id + ", AdminEmail=" + email + ", AdminPassword=" + password + "]";
	}

}

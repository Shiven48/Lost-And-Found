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
	private Integer adminId;
	
	@Column
	@Email
	@NotNull
	private String AdminEmail;
	
	@Column
	@Min(value = 8,message = "The password must be more than 8 alphabets")
	@NotNull
	private String AdminPassword;

	public Admin() {}
	
	public Admin(Integer adminId, @Email String adminEmail,
			@Min(value = 8, message = "The password must be more than 8 alphabets") String adminPassword) {
		super();
		this.adminId = adminId;
		AdminEmail = adminEmail;
		AdminPassword = adminPassword;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminEmail() {
		return AdminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		AdminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return AdminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		AdminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", AdminEmail=" + AdminEmail + ", AdminPassword=" + AdminPassword + "]";
	}

}

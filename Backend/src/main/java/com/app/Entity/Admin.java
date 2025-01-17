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
	private String email;
	
	@Column
	@Min(value = 8,message = "The password must be more than 8 alphabets")
	@NotNull
	private String password;

}

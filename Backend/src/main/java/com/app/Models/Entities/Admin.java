package com.app.Entity.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ADMIN_TABLE")
public class Admin extends Credentials
{
	@Column
	@NotNull
	private String name;

	public Admin(){ }

	public Admin(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Admin [" +
				"adminName="+ name +
				']';
	}
}

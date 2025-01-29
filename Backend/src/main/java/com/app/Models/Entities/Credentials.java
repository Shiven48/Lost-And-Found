package com.app.Models.Entities;

import com.app.Models.Common.BaseAudit;
import jakarta.persistence.*;

@Entity
@Table(name = "CREDENTIALS_TABLE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Credentials extends BaseAudit
{
	@Column
	protected String name;

	@Column(nullable = false, unique = true)
	protected String email;

	@Column(nullable = false)
	protected String password;

	@Column
	protected String roles;

	public Credentials() {
		super();
	}

	public Credentials(Long id){
		super(id);
	}

	public Credentials(String email, String password, String name, String roles) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.roles = roles;
	}

	public Credentials(Long id, String email, String password) {
        super(id);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId(){
		return super.id;
	}

	@Override
	public String toString() {
		return "Credentials{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", roles='" + roles + '\'' +
				", registrationDate=" + registrationDate +
				", lastModified=" + lastModified +
				", id=" + id +
				'}';
	}
}

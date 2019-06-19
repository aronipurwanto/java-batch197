package com.xsis.batch197.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="tbl_user")
public class UserModel {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="user_idx")
	@TableGenerator(name="user_idx", table="tbl_index", pkColumnName="index_id", valueColumnName="index_value", initialValue=0, allocationSize=1)
	@Column(name="id")
	private long id;
	
	@Column(name="username", nullable=false, length=64)
	private String username;

	@Column(name="email", nullable=false, length=64)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="is_active", nullable=false)
	private int active;
	
	@Column(name="roles")
	private String roles="";
	
	@Column(name="permission")
	private String permission="";
	
	public UserModel() {
	}
	
	public UserModel(String username,String email, String password, String roles, String permission) {
		this.username=username;
		this.email = email;
		this.password=password;
		this.roles=roles;
		this.permission=permission;
		this.active=1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public List<String> getRolesList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
	public List<String> getPermisstionList(){
		if(this.permission.length() > 0) {
			return Arrays.asList(this.permission.split(","));
		}
		return new ArrayList<>();
	}
}

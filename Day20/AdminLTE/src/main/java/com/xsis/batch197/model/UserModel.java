package com.xsis.batch197.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="tbl_user")
public class UserModel extends BaseModel {
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
	
	@Column(name="is_active", nullable=false, length=1)
	private int active;
	
	@Column(name="is_locked", nullable=false, length=1)
	private int isLocked;
	
	@Column(name="is_expired", nullable=false, length=1)
	private int isExpired;
	
	@Column(name="permission")
	private String permission="";
	
	@ManyToMany
	@JoinTable(
			name="tbl_user_role", 
			joinColumns=@JoinColumn(name="user_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_user_role_id")), 
			inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_role_user_id")),
			foreignKey = @ForeignKey(name="fk_user_role_id"),
			inverseForeignKey = @ForeignKey(name="fk_role_user_id")
	)
	private List<RoleModel> roleList = new ArrayList<RoleModel>();
	
	public UserModel() {
		super();
	}
	
	public UserModel(String username,String email, String password, String permission) {
		super();
		this.username=username;
		this.email = email;
		this.password=password;
		this.permission=permission;
		this.active=1;
		this.isExpired=0;
		this.isLocked=0;
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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	public int getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(int isExpired) {
		this.isExpired = isExpired;
	}

	public List<String> getPermisstionList(){
		if(this.permission.length() > 0) {
			return Arrays.asList(this.permission.split(","));
		}
		return new ArrayList<>();
	}

	public List<RoleModel> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}
}

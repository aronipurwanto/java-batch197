package com.xsis.batch197.model;

import java.util.ArrayList;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tbl_role")
public class RoleModel extends BaseModel{
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="role_idx")
	@TableGenerator(name="role_idx", table="tbl_index", pkColumnName="index_id", valueColumnName="index_value", initialValue=0, allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@NotEmpty
	@Column(name="code", nullable=false, length=50)
	private String code;
	
	@NotBlank
	@NotEmpty
	@Column(name="name", nullable=false, length=50)
	private String name;
	
	@ManyToMany(mappedBy="roleList")
	private List<UserModel> userList = new ArrayList<UserModel>();
	
	@ManyToMany
	@JoinTable(
			name="tbl_role_menu", 
			joinColumns=@JoinColumn(name="role_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_role_menu_id")), 
			inverseJoinColumns=@JoinColumn(name="menu_id", referencedColumnName="id", foreignKey=@ForeignKey(name="fk_menu_role_id")),
			foreignKey = @ForeignKey(name="fk_role_menu_id"),
			inverseForeignKey = @ForeignKey(name="fk_menu_role_id")
	)
	private List<MenuModel> menuList = new ArrayList<MenuModel>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}

	public List<MenuModel> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuModel> menuList) {
		this.menuList = menuList;
	}
}

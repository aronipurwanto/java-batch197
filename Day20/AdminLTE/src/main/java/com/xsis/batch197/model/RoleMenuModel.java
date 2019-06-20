package com.xsis.batch197.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="tbl_role_menu")
public class RoleMenuModel extends BaseModel {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="role_menu_idx")
	@TableGenerator(name="role_menu_idx", table="tbl_index", pkColumnName="index_id", valueColumnName="index_value", initialValue=0, allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@Column(name="role_id", nullable=false, length=11)
	private Long roleId;
	
	@Column(name="menu_id", nullable=false, length=11)
	private Long menuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}

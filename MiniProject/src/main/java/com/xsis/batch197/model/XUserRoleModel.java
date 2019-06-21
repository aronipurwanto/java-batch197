package com.xsis.batch197.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "x_userrole")
public class XUserRoleModel extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "x_userrole_idx")
	@TableGenerator(name = "x_userrole_idx", table = "x_index", pkColumnName = "index_id", valueColumnName = "index_value", initialValue = 0, allocationSize = 1)
	@Column(name = "id", length = 11)
	private Long id;

	@NotNull
	@Column(name = "addrbook_id", length = 11, nullable = false)
	private Long addrbookId;

	@NotNull
	@Column(name = "role_id", length = 11, nullable = false)
	private Long roleId;

	public XUserRoleModel() {
		super();
	}
	
	public XUserRoleModel(Long userId) {
		super(userId);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAddrbookId() {
		return addrbookId;
	}

	public void setAddrbookId(Long addrbookId) {
		this.addrbookId = addrbookId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}

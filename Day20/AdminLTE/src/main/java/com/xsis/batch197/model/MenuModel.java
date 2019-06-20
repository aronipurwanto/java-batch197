package com.xsis.batch197.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tbl_menu")
public class MenuModel extends BaseModel {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="menu_idx")
	@TableGenerator(name="menu_idx", table="tbl_index", pkColumnName="index_id", valueColumnName="index_value", initialValue=0, allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@NotEmpty
	@Column(name="menu_title", nullable=false, length=50)
	private String title;
	
	@NotBlank
	@NotEmpty
	@Column(name="menu_image_url", nullable=false, length=100)
	private String imageUrl;
	
	@NotBlank
	@NotEmpty
	@Column(name="menu_icon", nullable=false, length=100)
	private String icon;
	
	@Column(name="menu_order", nullable=false, length=2)
	private Integer order;
	
	@Column(name="menu_level", nullable=false, length=1)
	private Integer level;
	
	@Column(name="parent_id", nullable=false, length=11)
	private Long parentId;
	
	@Column(name="menu_url", nullable=false, length=100)
	private String url;
	
	@Column(name="menu_type", nullable=false, length=10)
	private String type;
	
	@ManyToMany(mappedBy="menuList")
	private List<RoleModel> roleList = new ArrayList<RoleModel>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RoleModel> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}
}

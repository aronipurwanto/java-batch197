package com.xsis.batch197.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "x_addrbook")
public class XAddressBookModel extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "x_addrbook_idx")
	@TableGenerator(name = "x_addrbook_idx", table = "x_index", pkColumnName = "index_id", valueColumnName = "index_value", initialValue = 0, allocationSize = 1)
	@Column(name = "id" , length = 11)
	private Long id;

	@NotNull
	@Column(name = "is_locked", nullable = false, length=1)
	private Integer isLocked;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "abuid", length = 50, nullable = false)
	private String abuid;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "abpwd", length = 50, nullable = false)
	private String abpwd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbuid() {
		return abuid;
	}

	public void setAbuid(String abuid) {
		this.abuid = abuid;
	}

	public String getAbpwd() {
		return abpwd;
	}

	public void setAbpwd(String abpwd) {
		this.abpwd = abpwd;
	}

}

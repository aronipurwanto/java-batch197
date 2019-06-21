package com.xsis.batch197.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "x_undangan_detail")
public class XUndanganDetailModel extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "x_undangan_detail_idx")
	@TableGenerator(name = "x_undangan_detail_idx", table = "x_index", pkColumnName = "index_id", valueColumnName = "index_value", initialValue = 0, allocationSize = 1)
	@Column(name = "id", length = 11)
	private Long id;

	@Column(name = "undangan_id", length = 11, nullable = false)
	private Long undanganId;

	@Column(name = "biodata_id", length = 11, nullable = false)
	private Long biodataId;

	@Column(name = "notes", length = 1000, nullable = true)
	private String notes;

	public XUndanganDetailModel() {
		super();
	}
	
	public XUndanganDetailModel(Long userId, Long biodataId) {
		super(userId);
		this.biodataId = biodataId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUndanganId() {
		return undanganId;
	}

	public void setUndanganId(Long undanganId) {
		this.undanganId = undanganId;
	}

	public Long getBiodataId() {
		return biodataId;
	}

	public void setBiodataId(Long biodataId) {
		this.biodataId = biodataId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}

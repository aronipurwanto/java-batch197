package com.xsis.batch197.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="x_keahlian")
public class XKeahlianModel extends BaseModel{

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="x_keahlian_idx")
	@TableGenerator(name="x_keahlian_idx", table="x_index", pkColumnName="index_id", valueColumnName="index_value", initialValue=0, allocationSize=1)
	@Column(name="id", length=11)
	private Long id;
	
	@Column(name="biodata_id", nullable=false, length=11)
	private Long biodataId;
	
	@Column(name="skill_name", nullable=true, length=100)
	private String skillName;
	
	@Column(name="skill_level_id", nullable=true, length=11)
	private Long skillLevelId;
	
	@Column(name="notes", nullable=true, length=1000)
	private String notes;
	
	public XKeahlianModel() {
		super();
	}
	
	public XKeahlianModel(Long userId) {
		super(userId);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBiodataId() {
		return biodataId;
	}

	public void setBiodataId(Long biodataId) {
		this.biodataId = biodataId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Long getSkillLevelId() {
		return skillLevelId;
	}

	public void setSkillLevelId(Long skillLevelId) {
		this.skillLevelId = skillLevelId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}

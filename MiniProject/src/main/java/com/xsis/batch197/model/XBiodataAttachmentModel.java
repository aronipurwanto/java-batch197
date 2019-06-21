package com.xsis.batch197.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="x_biodata_attachment")
public class XBiodataAttachmentModel extends BaseModel {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="x_biodata_attachment_idx")
	@TableGenerator(name="x_biodata_attachment_idx", table="x_index", pkColumnName="index_id", valueColumnName="index_value", initialValue=0, allocationSize=1)
	@Column(name="id", length=11)
	private Long id;
	
	@NotNull
	@Column(name="biodata_id", nullable=false, length=11)
	private Long biodataId;
	
	@Column(name="file_name", nullable=true, length=100)
	private String fileName;
	
	@Column(name="file_path", nullable=true, length=1000)
	private String filePath;
	
	@Column(name="notes", nullable=true, length = 1000)
	private String notes;
	
	@Column(name="is_photo", nullable=true)
	private Boolean isPhoto;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getIsPhoto() {
		return isPhoto;
	}

	public void setIsPhoto(Boolean isPhoto) {
		this.isPhoto = isPhoto;
	}
	
}

package com.persistence.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userModify;

	private Date dateModify;

	private Date dateInsert;

	private String userInsert;

	private Integer version;

	public BaseEntity() {
		super();
	}

	@CreatedDate
	@Column(name = "DATE_INSERT")
	public Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	@LastModifiedDate
	@Column(name = "DATE_MODIFY")
	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	@CreatedBy
	@Column(name = "USER_INSERT")
	public String getUserInsert() {
		return userInsert;
	}

	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}
	
	@LastModifiedBy
	@Column(name = "USER_MODIFY")
	public String getUserModify() {
		return userModify;
	}

	public void setUserModify(String userModify) {
		this.userModify = userModify;
	}

	@Version
	@NotNull
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

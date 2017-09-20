package commons.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import commons.model.annotation.Audit;

@Audit
@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity {

	private static final long serialVersionUID = 1L;

	private String userModify;

	private Date dateModify;

	private Date dateInsert;

	private String userInsert;

	private Integer version;

	public BaseEntity() {
		super();
	}

	@Column(name = "USER_MODIFY")
	public String getUserModify() {
		return userModify;
	}

	public void setUserModify(String userModify) {
		this.userModify = userModify;
	}

	@Column(name = "DATE_INSERT")
	public Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	@Column(name = "DATE_MODIFY")
	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	@Column(name = "USER_INSERT")
	public String getUserInsert() {
		return userInsert;
	}

	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
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
	public int compareTo(Object obj) {
		return this.compareTo(obj);
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

package commons.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

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

//    @Transient
////    public abstract Long getId();
//    public Long getId(){
//       return this.id;
//    }
//
////    @Transient
////    public abstract void setId(Long id);
//    public  void setId(Long id){}

	@Column(name = "USER_MODIFY", updatable = true)
	public String getUserModify() {
		return userModify;
	}

	public void setUserModify(String userModify) {
		this.userModify = userModify;
	}

	@Column(name = "DATE_INSERT", updatable = false)
	public Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	@Column(name = "DATE_MODIFY", updatable = true)
	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	@Column(name = "USER_INSERT", updatable = false)
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

	@SuppressWarnings("unchecked")
	public int compareTo(Object arg0) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public boolean equals(Object obj) {
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public int hashCode() {
		throw new RuntimeException("not implemented yet");
	}
}

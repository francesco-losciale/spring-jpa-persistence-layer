package persistence2.helpers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;

@SuppressWarnings("serial")
@MappedSuperclass
@FilterDef(name = "not_deleted")
@Filters({@Filter(name = "not_deleted", condition = "DATE_DELETE is null")})
public abstract class LogicDeleteEntity extends BaseEntity implements ILogicDeleteEntity {

	public static final String DATE_DELETE = "dateDelete";

	private String userDelete;

	private Date dateDelete;

	@Column(name = "USER_DELETE", length = 50)
	public String getUserDelete() {
		return this.userDelete;
	}

	public void setUserDelete(String userDelete) {
		this.userDelete = userDelete;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_DELETE")
	public Date getDateDelete() {
		return this.dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}
}

package commons.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * EVita di copiare le proprietà contenute in BaseEntity
 *
 * @author Riccardo Fedel (riccardo.fedel@finconsgroup.com)
 */
@SuppressWarnings("serial")
public class BaseEntityDTO extends EntityDTO implements IBaseEntityDTO {

	public static final String USER_MODIFY = "userModify";

	public static final String DATE_INSERT = "dateInsert";

	public static final String DATE_MODIFY = "dateModify";

	public static final String USER_INSERT = "userInsert";

	private String userModify;

	@DateTimeFormat
	private Date dateInsert;

	@DateTimeFormat
	private Date dateModify;

	private String userInsert;

	private Integer version;

	public String getUserModify() {
		return userModify;
	}

	public void setUserModify(String userModify) {
		this.userModify = userModify;
	}

	public Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	public String getUserInsert() {
		return userInsert;
	}

	public void setUserInsert(String userInsert) {
		this.userInsert = userInsert;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

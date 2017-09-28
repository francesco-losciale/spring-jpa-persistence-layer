package persistence.helpers.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * EVita di copiare le proprietà contenute in LogicDeleteEntity
 *
 * @author Padrin Stefano (stefano.padrin@finconsgroup.com)
 */
@SuppressWarnings("serial")
public class LogicDeleteDTO extends BaseEntityDTO implements ILogicDeleteDTO {

	public static final String DATE_DELETE = "dateDelete";

	public static final String USER_DELETE = "userDelete";

	private String userDelete;

	@DateTimeFormat
	private Date dateDelete;

	public String getUserDelete() {
		return userDelete;
	}

	public void setUserDelete(String userDelete) {
		this.userDelete = userDelete;
	}

	public Date getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}
}

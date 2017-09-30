package persistence2.helpers.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("serial")
public class OperationMetadataDTO implements Serializable, Cloneable {

	private String username;

	private Date dateCalendar;

	private Locale locale;

	public OperationMetadataDTO(String username, Date dateCalendar, Locale locale) {
		super();
		this.username = username;
		this.dateCalendar = dateCalendar;
		this.locale = locale;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateCalendar() {
		return dateCalendar;
	}

	public void setDateCalendar(Date dateCalendar) {
		this.dateCalendar = dateCalendar;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}

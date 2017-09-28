package persistence.helpers.bean;

import java.util.Date;
import java.util.Locale;

import persistence.helpers.dto.OperationMetadataDTO;

@SuppressWarnings("serial")
public class OperationMetadata extends OperationMetadataDTO implements Comparable<OperationMetadata>, Metadata, Cloneable {

	public OperationMetadata(String username, Date dateCalendar, Locale locale) {
		super(username, dateCalendar, locale);
	}

	public OperationMetadata(String username, Locale locale) {
		super(username, new Date(), locale);
	}

	public String getUniqueKey() {
		return getUsername() + getDateCalendar();
	}


	@Override
	public String toString() {
		return super.toString() + ", id:" + getUsername();
	}

	@Override
	public int compareTo(OperationMetadata o) {
		throw new RuntimeException("not implemented yet");
	}

}

package commons.model.bean;

import java.util.Date;
import java.util.Locale;

import commons.cache.ICacheable;
import commons.model.dto.OperationMetadataDTO;

@SuppressWarnings("serial")
public class OperationMetadata extends OperationMetadataDTO implements ICacheable, Comparable<OperationMetadata>, Metadata, Cloneable {

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

	@Override
	public boolean equals(Comparable<?> par) {
		// TODO Auto-generated method stub
		return false;
	}

}

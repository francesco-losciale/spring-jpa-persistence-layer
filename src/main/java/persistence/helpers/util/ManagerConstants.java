package persistence.helpers.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public interface ManagerConstants {

	public static final String OPERATION_METADATA = "OPERATION_METADATA";

	public static Date DATE_DELETE_IS_NULL = new GregorianCalendar(0, Calendar.JANUARY, 1).getTime();
}

package persistence2.helpers.dto;

import java.util.Date;

/**
 * EVita di copiare le proprietà contenute in LogicDeleteEntity
 *
 * @author Padrin Stefano (stefano.padrin@finconsgroup.com)
 */

public interface ILogicDeleteDTO extends ISimpleEntityDTO {

	public static final String DATE_DELETE = "dateDelete";

	public static final String USER_DELETE = "userDelete";

	public String getUserDelete();

	public void setUserDelete(String userDelete);

	public Date getDateDelete();

	public void setDateDelete(Date dateDelete);
}

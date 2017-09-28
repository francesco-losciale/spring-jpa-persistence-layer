package persistence.helpers.dto;

import java.util.Date;

/**
 * EVita di copiare le proprietà contenute in BaseEntity
 *
 * @author Riccardo Fedel (riccardo.fedel@finconsgroup.com)
 */

public interface IBaseEntityDTO extends ISimpleEntityDTO {

	public static final String USER_MODIFY = "userModify";

	public static final String DATE_INSERT = "dateInsert";

	public static final String DATE_MODIFY = "dateModify";

	public static final String USER_INSERT = "userInsert";

	public String getUserModify();

	public void setUserModify(String userModify);

	public Date getDateInsert();

	public void setDateInsert(Date dateInsert);

	public Date getDateModify();

	public void setDateModify(Date dateModify);

	public String getUserInsert();

	public void setUserInsert(String userInsert);

	public Integer getVersion();

	public void setVersion(Integer version);
}

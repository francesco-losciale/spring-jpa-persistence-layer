package persistence2.helpers.entity;

import java.util.Date;

public interface ILogicDeleteEntity extends IBaseEntity {

	public static final String DATE_DELETE = "dateDelete";

	public static final String USER_DELETE = "userDelete";

	public String getUserDelete();

	public void setUserDelete(String userDelete);

	public Date getDateDelete();

	public void setDateDelete(Date dateDelete);
}

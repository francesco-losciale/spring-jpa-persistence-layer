package persistence2.helpers.entity;

import java.util.Date;

public interface IBaseEntity extends ISimpleEntity<Long> {
//public interface IBaseEntity {
	//gigi

	public static final String USER_MODIFY = "userModify";

	public static final String DATE_INSERT = "dateInsert";

	public static final String DATE_MODIFY = "dateModify";

	public static final String USER_INSERT = "userInsert";

	public Long getId();

	public void setId(Long id);

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

	public int compareTo(Object arg0);

	public boolean equals(Object obj);

	public int hashCode();
}

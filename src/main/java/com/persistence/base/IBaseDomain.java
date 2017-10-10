package com.persistence.base;

import java.util.Date;

public interface IBaseDomain {

	public String getUserModify();
	public Date getDateModify();
	public Date getDateInsert();
	public String getUserInsert();
	public Date getDateDelete();
	public String getUserDelete();
	public Integer getVersion();
}

package domain.model;

import java.util.List;

import com.persistence.base.IBaseDomain;

//TODO this interface is extended only for test purpose, but here you should only
//place method's name that represent domain model logic
public interface TestCollection extends IBaseDomain { 

	public Long getId();
	public String getReleaseName();
	public List<Test> getListTest();
	
}

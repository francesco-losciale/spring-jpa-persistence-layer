package main.domain.model;

import com.persistence.base.IBaseDomain;

//TODO this interface is extended only for test purpose, but here you should only
// place method's name that represent domain model logic
public interface Test extends IBaseDomain {

	public Long getId();
    public TestCollection getTestCollection();
}

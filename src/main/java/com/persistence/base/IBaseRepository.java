package com.persistence.base;

import java.util.List;

public interface IBaseRepository<DomainObjectType extends BaseDomain, EntityObjectType extends BaseEntity>  {

	public DomainObjectType add(DomainObjectType domainObject);
	
	public DomainObjectType set(DomainObjectType domainObject);
	
	public void remove(DomainObjectType domainObject);
	
	public DomainObjectType get(Object id, String idFieldName);
	
	public List<DomainObjectType> getAll();
	
	public List<DomainObjectType> getPage(int pageNumber, int pageSize);
	
	public long count();
	
	public DomainObjectType convert(EntityObjectType entityObject);
	
	public EntityObjectType convert(DomainObjectType domainObject);
		
}

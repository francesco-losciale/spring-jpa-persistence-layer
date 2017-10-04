package com.persistence.base;

public interface IBaseRepository<DomainObjectType extends BaseDomain, EntityObjectType extends BaseEntity>  {

	public DomainObjectType add(DomainObjectType domainObject);
	
	public DomainObjectType set(DomainObjectType domainObject);
	
	public DomainObjectType remove(DomainObjectType domainObject);
	
	public DomainObjectType get(Long id);
	
	public DomainObjectType convert(EntityObjectType entityObject);
	
	public EntityObjectType convert(DomainObjectType domainObject);
	
}

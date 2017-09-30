package com.persistence.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.persistence.operation.IDeleteEntityOperation;
import com.persistence.operation.IInsertEntityOperation;
import com.persistence.operation.IUpdateEntityOperation;

public class BaseRepository<DomainObjectType, EntityObjectType extends BaseEntity> {

	private Class<DomainObjectType> domainObjectTypeClass; // TODO needed when reading from database
	private Class<EntityObjectType> entityObjectTypeClass;
	
	@Autowired
	private IDeleteEntityOperation<EntityObjectType> deleteEntityOperation;
	
	@Autowired
	private IInsertEntityOperation<EntityObjectType> insertEntityOperation;
	
	@Autowired
	private IUpdateEntityOperation<EntityObjectType> updateEntityOperation;
	
	protected BaseRepository(Class<DomainObjectType> domainObjectTypeClass, Class<EntityObjectType> entityObjectTypeClass) {
		this.domainObjectTypeClass = domainObjectTypeClass;
		this.entityObjectTypeClass = entityObjectTypeClass;	
	}
	
	public void add(DomainObjectType domainObject) {
		
		EntityObjectType entityObject = newEntityObjectType();
		
		// TODO map domain object to a new entity object 
		
		insertEntityOperation.insert(entityObject);
		
	}
	
	public void set(DomainObjectType domainObject) {
		
		EntityObjectType entityObject = newEntityObjectType();
		
		// TODO map domain object to a new entity object 
		
		updateEntityOperation.update(entityObject);
				
	}
	
	public void remove(DomainObjectType domainObject) {
		
		EntityObjectType entityObject = newEntityObjectType();
		
		// TODO map domain object to a new entity object 
		
		deleteEntityOperation.delete(entityObject);		
		
	}
	
	private EntityObjectType newEntityObjectType() {
		try {
			return entityObjectTypeClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Domain Model Object mapping error", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Domain Model Object mapping error", e);
		}
	}
	
}

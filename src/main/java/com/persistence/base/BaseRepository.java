package com.persistence.base;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.persistence.operation.IDeleteEntityOperation;
import com.persistence.operation.IGetEntityOperation;
import com.persistence.operation.IInsertEntityOperation;
import com.persistence.operation.IUpdateEntityOperation;

public abstract class BaseRepository<DomainObjectType extends BaseDomain, EntityObjectType extends BaseEntity> implements IBaseRepository<DomainObjectType , EntityObjectType> {

	private Class<DomainObjectType> domainObjectTypeClass; // TODO needed when reading from database
	private Class<EntityObjectType> entityObjectTypeClass;
	
	@Autowired
	private IDeleteEntityOperation<EntityObjectType> deleteEntityOperation;
	
	@Autowired
	private IInsertEntityOperation<EntityObjectType> insertEntityOperation;
	
	@Autowired
	private IUpdateEntityOperation<EntityObjectType> updateEntityOperation;
	
	@Autowired
	private IGetEntityOperation<EntityObjectType> getEntityOperation;
	
	
	protected BaseRepository(Class<DomainObjectType> domainObjectTypeClass, Class<EntityObjectType> entityObjectTypeClass) {
		this.domainObjectTypeClass = domainObjectTypeClass;
		this.entityObjectTypeClass = entityObjectTypeClass;	
	}
	
	@Override
	public DomainObjectType add(DomainObjectType domainObject) {
		EntityObjectType entityObject = this.convert(domainObject);
		entityObject = insertEntityOperation.insert(entityObject);	
		return this.convert(entityObject);
	}
	
	@Override
	public DomainObjectType set(DomainObjectType domainObject) {		
		EntityObjectType entityObject = this.convert(domainObject);		
		entityObject = updateEntityOperation.update(entityObject);		
		return this.convert(entityObject);
	}
	
	@Override
	public DomainObjectType remove(DomainObjectType domainObject) {		
		EntityObjectType entityObject = this.convert(domainObject);		
		entityObject = deleteEntityOperation.delete(entityObject);
		return this.convert(entityObject);
	}

	@Override
	public DomainObjectType get(Long id) {
		EntityObjectType entityObject = getEntityOperation.get(entityObjectTypeClass, id);
		return this.convert(entityObject);	
	}
		
	public CriteriaBuilder getCriteriaBuilder() {
		return getEntityOperation.getEntityManager().getCriteriaBuilder();
	}
	
	public List<EntityObjectType> executeQuery(CriteriaQuery<EntityObjectType> criteriaQuery) {
		return getEntityOperation.getEntityManager().createQuery(criteriaQuery).getResultList();
	}
		
}

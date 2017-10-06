package com.persistence.base;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.persistence.annotation.LogicalEntityDelete;
import com.persistence.operation.IDeleteEntityOperation;
import com.persistence.operation.IGetEntityOperation;
import com.persistence.operation.IInsertEntityOperation;
import com.persistence.operation.IUpdateEntityOperation;

public abstract class BaseRepository<DomainObjectType extends BaseDomain, EntityObjectType extends BaseEntity> implements IBaseRepository<DomainObjectType , EntityObjectType> {

	private Class<EntityObjectType> entityObjectTypeClass;
	
	@Autowired
	private IDeleteEntityOperation<EntityObjectType> deleteEntityOperation;
	
	@Autowired
	private IInsertEntityOperation<EntityObjectType> insertEntityOperation;
	
	@Autowired
	private IUpdateEntityOperation<EntityObjectType> updateEntityOperation;
	
	@Autowired
	private IGetEntityOperation<EntityObjectType> getEntityOperation;
	
	
	protected BaseRepository(Class<EntityObjectType> entityObjectTypeClass) {
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
	public void remove(DomainObjectType domainObject) {		
		EntityObjectType entityObject = this.convert(domainObject);		
		deleteEntityOperation.delete(entityObject);
	}
		
	public CriteriaBuilder getCriteriaBuilder() {
		return getEntityOperation.getEntityManager().getCriteriaBuilder();
	}
	
	protected List<EntityObjectType> executeQuery(CriteriaQuery<EntityObjectType> criteriaQuery, Root<EntityObjectType> root) {
		if (entityObjectTypeClass.isAnnotationPresent(LogicalEntityDelete.class)) {
			criteriaQuery = criteriaQuery.where(getCriteriaBuilder().isNull(root.get("dateDelete")));
		}		
		return getEntityOperation.getEntityManager().createQuery(criteriaQuery).getResultList();
	}
	
	public List<DomainObjectType> getAll() {
		
		CriteriaQuery<EntityObjectType> q = getCriteriaBuilder().createQuery(entityObjectTypeClass);
		Root<EntityObjectType> c = q.from(entityObjectTypeClass);		
		q.select(c);
		
		List<EntityObjectType> entityList = executeQuery(q, c);
		List<DomainObjectType> domainList = entityList.stream().map(t -> convert(t)).collect(Collectors.toList());
		
		return domainList;
	}
		
	public DomainObjectType get(Object id, String idFieldName) {
		
		CriteriaQuery<EntityObjectType> q = getCriteriaBuilder().createQuery(entityObjectTypeClass);
		Root<EntityObjectType> c = q.from(entityObjectTypeClass);		
		q.select(c);
		
		q = q.where(getCriteriaBuilder().equal(c.get(idFieldName), id));
		List<EntityObjectType> entityList = executeQuery(q, c);
		if (entityList.size() > 1) {
			throw new RuntimeException("More than one row selected");
		}
		EntityObjectType entityObject = entityList.get(0);
		return convert(entityObject);
	}
		
}

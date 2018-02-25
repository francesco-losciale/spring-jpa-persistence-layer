package com.persistence.base;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class BaseRepositoryImpl<DomainObjectType, EntityObjectType extends BaseEntity> extends CrudRepository<EntityObjectType> implements BaseRepository<DomainObjectType , EntityObjectType> {

	private Class<EntityObjectType> entityObjectTypeClass;
		
	protected BaseRepositoryImpl(Class<EntityObjectType> entityObjectTypeClass) {
		this.entityObjectTypeClass = entityObjectTypeClass;	
	}
	
	@Override
	public DomainObjectType add(DomainObjectType domainObject) {
		EntityObjectType entityObject = this.convert(domainObject);
		entityObject = super.insert(entityObject);	
		return this.convert(entityObject);
	}
	
	@Override
	public DomainObjectType set(DomainObjectType domainObject) {		
		EntityObjectType entityObject = this.convert(domainObject);		
		entityObject = super.update(entityObject);		
		return this.convert(entityObject);
	}
	
	@Override
	public void remove(DomainObjectType domainObject) {		
		EntityObjectType entityObject = this.convert(domainObject);		
		super.delete(entityObject);
	}
		
	public CriteriaBuilder getCriteriaBuilder() {
		return super.getEntityManager().getCriteriaBuilder();
	}
	
	protected List<EntityObjectType> executePaginationQuery(CriteriaQuery<EntityObjectType> criteriaQuery, int pageNumber, int pageSize) {
		TypedQuery<EntityObjectType> typedQuery = super.getEntityManager().createQuery(criteriaQuery);
	    typedQuery.setFirstResult(pageNumber - 1);
	    typedQuery.setMaxResults(pageSize);
	    
		return typedQuery.getResultList();
	}
	
	protected List<EntityObjectType> executeQuery(CriteriaQuery<EntityObjectType> criteriaQuery) {
		return super.getEntityManager().createQuery(criteriaQuery).getResultList();
	}	
		
	public List<DomainObjectType> getAll() {
		
		CriteriaQuery<EntityObjectType> criteriaQuery = getCriteriaBuilder().createQuery(entityObjectTypeClass);
		Root<EntityObjectType> root = criteriaQuery.from(entityObjectTypeClass);		
		criteriaQuery = criteriaQuery.select(root);
		
		List<EntityObjectType> entityList = executeQuery(criteriaQuery);
		List<DomainObjectType> domainList = entityList.stream().map(t -> convert(t)).collect(Collectors.toList());
		
		return domainList;
	}
	
	public List<DomainObjectType> getPage(int pageNumber, int pageSize) {
		
		CriteriaQuery<EntityObjectType> criteriaQuery = getCriteriaBuilder().createQuery(entityObjectTypeClass);
		Root<EntityObjectType> root = criteriaQuery.from(entityObjectTypeClass);		
		criteriaQuery = criteriaQuery.select(root);
			    
		List<EntityObjectType> entityList = executePaginationQuery(criteriaQuery, pageNumber, pageSize);
		List<DomainObjectType> domainList = entityList.stream().map(t -> convert(t)).collect(Collectors.toList());
		
		return domainList;
	}
	
	public long count() {
		
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(Long.class);
		countQuery.select(getCriteriaBuilder().count(countQuery.from(entityObjectTypeClass)));
		Long count = super.getEntityManager().createQuery(countQuery).getSingleResult();
		
		return count;
	}
		
	public DomainObjectType get(Object id, String idFieldName) {
		
		CriteriaQuery<EntityObjectType> criteriaQuery = getCriteriaBuilder().createQuery(entityObjectTypeClass);
		Root<EntityObjectType> root = criteriaQuery.from(entityObjectTypeClass);		
		criteriaQuery.select(root);
		
		criteriaQuery = criteriaQuery.where(getCriteriaBuilder().equal(root.get(idFieldName), id));
		List<EntityObjectType> entityList = executeQuery(criteriaQuery);
		if (entityList.size() > 1) {
			throw new RuntimeException("More than one row selected");
		}
		if (entityList.size() == 0) {
			throw new RuntimeException("No selectable record");
		}
		EntityObjectType entityObject = entityList.get(0);		
		return convert(entityObject);
	}
		
}

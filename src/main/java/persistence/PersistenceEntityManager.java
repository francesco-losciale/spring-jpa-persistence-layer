package persistence;


import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import commons.model.bean.AllMetadata;
import commons.model.bean.Metadata;
import commons.model.bean.OperationMetadata;
import commons.model.bean.TransferMetadata;
import commons.model.converter.IEntityConverter;
import commons.model.dto.ILogicDeleteDTO;
import commons.model.dto.ISimpleEntityDTO;
import commons.model.entity.IBaseEntity;
import commons.model.entity.LogicDeleteEntity;
import commons.model.exception.ManagerException;
import commons.model.exception.NonUniqueResultException;
import commons.model.expression.SimpleExpression;
import commons.model.operation.CriteriaOperation;
import commons.model.operation.EjbqlOperation;
import commons.model.operation.ICriteriaOperation;
import commons.model.operation.IDeleteEntityOperation;
import commons.model.operation.IEjbqlOperation;
import commons.model.operation.ISaveEntityOperation;
import commons.model.util.ManagerHelper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import commons.model.util.ReflectionHelper;

public class PersistenceEntityManager<T extends ISimpleEntityDTO, E extends IBaseEntity> extends BasePersistenceManager implements IPersistenceEntityManager<T, E> {

	private Class<T> dtoClass;
	private Class<E> entityClass;

	@Autowired
	private ISaveEntityOperation<E> saveOperation;

	@Autowired
	private IDeleteEntityOperation<E> deleteOperation;

	@Autowired
	@Qualifier(CriteriaOperation.NAME)
	private ICriteriaOperation<E> criteriaOperation;

	@Autowired
	@Qualifier(EjbqlOperation.NAME)
	private IEjbqlOperation<E> ejbqlOperation;

	@Autowired
	private IEntityConverter<T, E> converter;

	public PersistenceEntityManager(SessionFactory sessionFactory, Class<T> dtoClass, Class<E> entityClass, ISaveEntityOperation<E> saveOperation, IDeleteEntityOperation<E> deleteOperation, ICriteriaOperation<E> criteriaOperation, IEjbqlOperation<E> ejbqlOperation, IEntityConverter<T, E> converter) {
		this();
		super.setSessionFactory(sessionFactory);
		this.dtoClass = dtoClass;
		this.entityClass = entityClass;
		this.saveOperation = saveOperation;
		this.deleteOperation = deleteOperation;
		this.criteriaOperation = criteriaOperation;
		this.ejbqlOperation = ejbqlOperation;
		this.converter = converter;
	}

	public PersistenceEntityManager() {
		super();
		trovaType();
	}

	public PersistenceEntityManager(Class<T> dtoClass) {
		this();
		this.dtoClass = dtoClass;
	}

	private void trovaType() {
		if (this.dtoClass != null) return;
		try {
			Type gs = getClass().getGenericSuperclass();
			ParameterizedType pt;
			if (gs instanceof ParameterizedType) {
				pt = (ParameterizedType) gs;
				Type[] ata = pt.getActualTypeArguments();
				if (ata != null && ata.length > 0) try {
					this.dtoClass = (Class<T>) ata[0];
				} catch (Throwable t) {
				}

				if (ata != null && ata.length > 1) try {
					this.entityClass = (Class<E>) ata[1];
				} catch (Throwable t) {
				}
			}
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}

	protected Class<T> getPersistentClass() {
		trovaType();
		return dtoClass;
	}

	public void setPersistentClass(Class<T> dtoClass) {
		this.dtoClass = dtoClass;
	}

	protected Class<E> getEntityClass() {
		trovaType();
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getPersistentClassEntity() {
		Class<T> dto = getPersistentClass();
		return (Class<E>) ManagerHelper.getMapped(dto);
	}

	protected E readById(Long id, OperationMetadata metadata) {
		String query = "SELECT p FROM " + getPersistentClassEntity().getSimpleName() + " p where id='" + id + "'";

		SimpleExpression<String> expression = new SimpleExpression<String>(query, metadata);
		E entity = null;
		try {
			entity = ejbqlOperation.getSingle(expression);
		} catch (NonUniqueResultException e) {
			throw new ManagerException(e);
		}

		return entity;
	}

	public boolean exists(Long id, OperationMetadata metadata) {

		DetachedCriteria criteria = getCriteria();
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.idEq(id));

		Boolean value = null;
		try {
			value = criteriaOperation.executeSingle(criteria);
		} catch (NonUniqueResultException e) {
			throw new ManagerException(e);
		}
		return value;
	}

	protected E exist(Long id, OperationMetadata metadata) {
		DetachedCriteria criteria = getCriteria();
		criteria.setProjection(Projections.count("id"));
		criteria.add(Restrictions.idEq(id));

		E entity = null;
		try {
			entity = criteriaOperation.getSingle(criteria);
		} catch (NonUniqueResultException e) {
			throw new ManagerException(e);
		}
		return entity;
	}

	public T read(Long id, OperationMetadata metadata) {
		E e = readById(id, metadata);
		return entity2Dto(e, TransferMetadata.DEFAULT);
	}

	public T create(T dto, OperationMetadata metadata) {
		dto.setId(null);
		E entityDetach = dto2Entity(dto);
		E entity = saveOperation.save(entityDetach, metadata);
		return entity2Dto(entity, TransferMetadata.DEFAULT);
	}

	public T update(T dto, OperationMetadata metadata) {
		E entityDetach = dto2Entity(dto);
		E entity = saveOperation.save(entityDetach, metadata);
		return entity2Dto(entity, TransferMetadata.DEFAULT);
	}

	public T delete(T dto, OperationMetadata metadata) {
		E entity = deleteOperation.delete(dto2Entity(dto), metadata);
		return entity2Dto(entity, TransferMetadata.DEFAULT);
	}

	public List<T> list(OperationMetadata metadata) {
		return list(metadata, false);
	}

	public List<T> listAll(OperationMetadata metadata) {
		return list(metadata, true);
	}

	protected List<T> list(OperationMetadata metadata, boolean all) {
		throw new RuntimeException("not implemented yet");
	}

	public int count(OperationMetadata metadata) {
		return count(metadata, false);
	}

	public int countAll(OperationMetadata metadata) {
		return count(metadata, true);
	}

	public int count(OperationMetadata metadata, boolean all) {

		throw new RuntimeException("not implemented yet");
	}

	public List<T> findByExample(T o, Metadata... metadata) {
		throw new RuntimeException("not implemented yet");
	}

	public List<T> findByExample(T o, String[] orders, Metadata... metadata) {
		throw new RuntimeException("not implemented yet");
	}

	public List<T> findByExample(T o, Class<T> _class, Metadata... metadata) {
		return find(getCriteriaByExample(o), _class, metadata);
	}

	protected DetachedCriteria getCriteriaByExample(T o) {
		return getCriteriaByExample(o, null);
	}

	protected DetachedCriteria getCriteriaByExample(T o, String[] orders) {
		DetachedCriteria criteria = getCriteria();
		criteria = ManagerHelper.criteriaByExample(o, criteria, orders);
		return criteria;
	}

	protected DetachedCriteria getCriteria() {
		// creo il criteria
		Class<?> entityClass = getEntityInstance().getClass();
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass, "entityPartenza");
		return criteria;
	}

	protected List<T> find(DetachedCriteria criteria, Metadata... metadata) {
		return find(criteria, dtoClass, metadata);
	}

	protected List<T> find(DetachedCriteria criteria, AllMetadata allMetadata) {
		return find(criteria, dtoClass, allMetadata);
	}

	protected List<T> find(DetachedCriteria criteria, Class<T> _class, Metadata... metadata) {
		throw new RuntimeException("not implemented yet");
	}

	protected List<T> find(DetachedCriteria criteria, Class<T> _class, AllMetadata allMetadata) {
		throw new RuntimeException("not implemented yet");
	}

	protected E getEntityInstance(Class<? extends ISimpleEntityDTO> _class) {
		throw new RuntimeException("not implemented yet");
	}

	protected E getEntityInstance() {
		return getEntityInstance(dtoClass);
	}

	public List<T> simpleQuery(String hsql, OperationMetadata metadata) {

		SimpleExpression<String> expression = new SimpleExpression<String>(hsql, metadata);
		return entity2Dto(ejbqlOperation.getList(expression), new TransferMetadata());
	}

	@SuppressWarnings("unchecked")
	protected ISimpleEntityDTO getEntityDtoById(Long id, Class<? extends ISimpleEntityDTO> target, OperationMetadata metadata, IEntityConverter converter) {
		String entityName = getEntityInstance(target).getClass().getSimpleName();

		String hsql = "select e from " + entityName + " e where e.id = " + id;

		SimpleExpression<String> expression = new SimpleExpression<String>(hsql, metadata);

		E entity = null;
		try {
			entity = ejbqlOperation.getSingle(expression);
		} catch (NonUniqueResultException e) {
			throw new ManagerException(e);
		}

		return converter.entityToDto(entity, target, null);
	}

	public Class<T> getDtoClass() {
		return dtoClass;
	}

	public ISaveEntityOperation<E> getSaveOperation() {
		return saveOperation;
	}

	public IDeleteEntityOperation<E> getDeleteOperation() {
		return deleteOperation;
	}

	public ICriteriaOperation<E> getCriteriaOperation() {
		return criteriaOperation;
	}

	public IEjbqlOperation<E> getEjbqlOperation() {
		return ejbqlOperation;
	}

	public IEntityConverter<T, E> getConverter() {
		return converter;
	}

	public void setConverter(IEntityConverter<T, E> converter) {
		this.converter = converter;
	}

	protected E dto2Entity(T dto) {
		return converter.dtoToEntity(dto);
	}

	protected T entity2Dto(E entity, TransferMetadata mappingMetadata) {
		return (T) converter.entityToDto(entity, getDtoClass(), mappingMetadata == null ? TransferMetadata.DEFAULT : mappingMetadata);
	}

	protected List<T> entity2Dto(List<E> list, TransferMetadata mappingMetadata) {
		return (List<T>) converter.entityToDto(list, getDtoClass(), mappingMetadata == null ? TransferMetadata.DEFAULT : mappingMetadata);
	}

}

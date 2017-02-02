package commons.model.converter;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.Validate;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import commons.model.bean.TransferMetadata;
import commons.model.dto.ISimpleEntityDTO;
import commons.model.entity.IBaseEntity;
import commons.model.entity.ISimpleEntity;
import commons.model.exception.ManagerException;
import commons.model.transfer.Mapping;
import commons.model.util.HibernateUtils;
import commons.model.util.ManagerHelper;
import commons.model.util.ReflectionHelper;
@SuppressWarnings("unchecked")
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class EntityConverter<T extends ISimpleEntityDTO, E extends IBaseEntity> implements IEntityConverter<T, E> {

	public final static String NAME = "entityConverter";
	private static ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();

	public Collection<T> entityToDto(Collection<E> from, Class<T> classDTO, TransferMetadata mappingMetadata) {

		if (mappingMetadata == null) mappingMetadata = new TransferMetadata();

		if (from == null) return null;

		int size = from.size();
		Collection<T> listTo = null;
//
//		if (from instanceof ListPaginator<?>) {
//			listTo = new ListPaginator<T>(size);
//			// setto il paginatore
//			PaginatorDTO paginator = ((ListPaginator<?>) from).getPaginator();
//			((ListPaginator<?>) listTo).setPaginator(paginator);
//		} else {
//			listTo = new ArrayList<T>(size);
//		}

		for (E baseEntity : from) {
			listTo.add(entityToDto(baseEntity, classDTO, mappingMetadata));
		}
		return listTo;
	}

	/**
	 *
	 */
	public T entityToDto(E from, Class<T> classDTO, TransferMetadata mappingMetadata) {
		ISimpleEntity<?> fromSimple = from;
		return entityToDto(fromSimple, classDTO, mappingMetadata);
	}

	/**
	 *
	 */
	public T entityToDto(ISimpleEntity<?> from, Class<T> classDTO, TransferMetadata mappingMetadata) {

		if (mappingMetadata == null) mappingMetadata = TransferMetadata.DEFAULT;
		/*
		 * leggo la struttura di dto e quella di entity - se dto non ha annotato
		 * entity.class exception - leggo la struttura delle due classi - copio
		 * gli elementi non entity o dto - per gli elementi entity/dto rieseguo
		 * 'entiityToDto'
		 */
		if (from == null) return null;

		from = HibernateUtils.initializeAndUnproxy(from);

		Class<E> clsEntity = (Class<E>) ManagerHelper.getMapped(classDTO);
		Validate.notNull(clsEntity, "entity non è annotato ad una BaseEntity");

		if (clsEntity != from.getClass()) throw new ManagerException("dto non è annotato a " + from.getClass());

		return new Mapping().mapping(from, classDTO, mappingMetadata);
	}

	/**
	 *
	 */
	public E dtoToEntity(T from) {
		Class<E> entityClass = (Class<E>) ManagerHelper.getMapped(from);
		E to = (E) REFLECTION_HELPER.newInstance(entityClass);

		to = new Mapping().mapping(from, to);

		return to;
	}

	public List<T> entityToDto(List<E> from, Class<T> classDTO, TransferMetadata mappingMetadata) {
		if (mappingMetadata == null) mappingMetadata = new TransferMetadata();

		if (from == null) return null;

		int size = from.size();
		List<T> listTo = null;

//		if (from instanceof EntityListPaginator<?>) {
//			listTo = new ListPaginator<T>(size);
//			// includo il paginatore
//			PaginatorDTO paginator = ((EntityListPaginator<E>) from).getPaginator();
//			((ListPaginator<T>) listTo).setPaginator(paginator);
//		} else {
//			listTo = new ArrayList<T>(size);
//		}

		for (E baseEntity : from) {
			listTo.add(entityToDto(baseEntity, classDTO, mappingMetadata));
		}
		return listTo;
	}
}

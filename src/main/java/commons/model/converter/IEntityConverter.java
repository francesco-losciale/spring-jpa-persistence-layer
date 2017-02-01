package commons.model.converter;

import java.util.Collection;
import java.util.List;

import commons.model.bean.TransferMetadata;
import commons.model.dto.ISimpleEntityDTO;
import commons.model.entity.IBaseEntity;
import commons.model.entity.ISimpleEntity;

public interface IEntityConverter<T extends ISimpleEntityDTO, E extends IBaseEntity> {

	public Collection<T> entityToDto(Collection<E> from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public List<T> entityToDto(List<E> from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public T entityToDto(E from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public T entityToDto(ISimpleEntity<?> from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public E dtoToEntity(T from);
}

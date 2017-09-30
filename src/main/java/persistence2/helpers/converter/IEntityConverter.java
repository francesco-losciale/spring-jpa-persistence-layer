package persistence2.helpers.converter;

import java.util.Collection;
import java.util.List;

import persistence2.helpers.bean.TransferMetadata;
import persistence2.helpers.dto.ISimpleEntityDTO;
import persistence2.helpers.entity.IBaseEntity;
import persistence2.helpers.entity.ISimpleEntity;

public interface IEntityConverter<T extends ISimpleEntityDTO, E extends IBaseEntity> {

	public Collection<T> entityToDto(Collection<E> from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public List<T> entityToDto(List<E> from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public T entityToDto(E from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public T entityToDto(ISimpleEntity<?> from, Class<T> classDTO, TransferMetadata mappingMetadata);

	public E dtoToEntity(T from);
}

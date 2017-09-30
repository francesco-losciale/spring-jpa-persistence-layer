package persistence2.helpers.transfer;

import org.apache.commons.lang.Validate;

import persistence2.helpers.bean.TransferMetadata;
import persistence2.helpers.bean.TransferType;

@SuppressWarnings("unchecked")
public class Mapping {

	public <T> T mapping(final Object from, final T to) {
		final Object o = binding(from, to);
		return (T) naiveCast(o);
	}

	public <T> T mappingAll(final Object from, final T to) {
		final Object o = bindingAll(from, to);
		return (T) naiveCast(o);
	}

	public <T> T mapping(final Object from, final Class<T> clsTo, final TransferMetadata transferMetadata) {

		final Object o = binding(from, clsTo, transferMetadata);
		return (T) naiveCast(o);
	}

	public <T> T naiveCast(final Object o) {
		return (T) o;
	}

	public Object binding(final Object from, final Object to) {
		Validate.notNull(to, "to null");
		return binding(from, to.getClass(), TransferMetadata.DEFAULT);
	}

	public Object binding(final Object from, final Class<?> classTo) {
		Validate.notNull(classTo, "to null");
		return binding(from, classTo, TransferMetadata.DEFAULT);
	}

	public Object bindingAll(final Object from, final Object to) {
		Validate.notNull(to, "to null");
		return binding(from, to.getClass(), new TransferMetadata(TransferType.TUTTI));
	}

	public Object bindingAll(final Object from, final Class<?> classTo) {
		Validate.notNull(classTo, "to null");
		return binding(from, classTo, new TransferMetadata(TransferType.TUTTI));
	}

	public Object binding(final Object from, final Class<?> classTo, final TransferMetadata transferMetadata) {
		final TransferSession transferSession = new TransferSession(transferMetadata);
		final TransferFactory transferFactory = new TransferFactory(transferSession);
		final ITransfer transfer = transferFactory.getInstance(from.getClass());
		return transfer.esegui(from, classTo);
	}
}

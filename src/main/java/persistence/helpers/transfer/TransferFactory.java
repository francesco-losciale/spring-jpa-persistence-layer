package persistence.helpers.transfer;

import persistence.helpers.util.ReflectionHelper;

public class TransferFactory {

	private static final ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();
	private ITransfer transferSimple, transferArray, transferSet, transferCollection, transferObject, transferMap;
	private TransferSession sessionMapping;

	public TransferFactory(final TransferSession sessionMapping) {
		this.sessionMapping = sessionMapping;
		transferSimple = new TransferSimple(this);
		transferArray = new TransferArray(this);
		transferCollection = new TransferCollection(this);
		transferSet = new TransferSet(this);
		transferObject = new TransferObject(this);
		transferMap = new TransferMap(this);
	}

	public ITransfer getInstance(final Class<?> classFrom) {
		final TypeObject tipo = tipo(classFrom);
		switch (tipo) {
			case SIMPLE:
				return transferSimple;
			case ARRAY:
				return transferArray;
			case SET:
				return transferSet;
			case COLLECTION:
				return transferCollection;
			case MAP:
				return transferMap;
			case OBJECT:
				return transferObject;
			default:
				throw new TransferException("tipo non gestito");
		}
	}

	private TypeObject tipo(final Class<?> cls) {
		if (REFLECTION_HELPER.isMap(cls)) {
			return TypeObject.MAP;
		}
		if (REFLECTION_HELPER.isSet(cls)) {
			return TypeObject.SET;
		}
		if (REFLECTION_HELPER.isCollection(cls)) {
			return TypeObject.COLLECTION;
		}
		if (cls.isArray()) {
			return TypeObject.ARRAY;
		}
		if (REFLECTION_HELPER.isBase(cls)) {
			return TypeObject.SIMPLE;
		}

		return TypeObject.OBJECT;
	}

	public TransferSession getSessionMapping() {
		return sessionMapping;
	}
}

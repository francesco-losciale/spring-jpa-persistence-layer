package persistence2.helpers.transfer;

import java.io.Serializable;

import persistence2.helpers.bean.ClassPlus;

public class TransferSimple extends AbstractTransfer {

	public TransferSimple(final TransferFactory mappingFactory) {
		super(mappingFactory);
	}

	public Object execute(final Object from, final ClassPlus plusTo) {
		final Class<?> classTo = plusTo.getClasse();
		if (from == null) {
			return null;
		}
		final Class<?> classFrom = from.getClass();
		if (classFrom.equals(classTo) || (REFLECTION_HELPER.isBase(classTo) && REFLECTION_HELPER.isEqual(classFrom, classTo)) || ((classFrom == Long.class || classTo.equals(Long.class)) && classFrom.equals(Serializable.class) || classTo.equals(Serializable.class))) {
			return from;
		}
		return null;
	}

	@Override
	public final TypeObject getTipo() {
		return TypeObject.SIMPLE;
	}
}

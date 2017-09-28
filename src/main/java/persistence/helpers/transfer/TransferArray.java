package persistence.helpers.transfer;

import java.lang.reflect.Array;

import persistence.helpers.bean.ClassPlus;

public class TransferArray extends AbstractTransfer {

	public TransferArray(final TransferFactory mappingFactory) {
		super(mappingFactory);
	}

	public Object execute(final Object from, final ClassPlus plusTo) {
		final Class<?> classTo = plusTo.getClasse();
		if (super.getMapped().containsKey(from)) {
			return super.getMapped().get(from);
		}

		if (super.getMapping().containsKey(from)) {
			return null;
		}
		super.getMapping().put(from, from);

		final int n = Array.getLength(from);
		final Class<?> cTo = classTo.getComponentType();
		final Object arrayTo = Array.newInstance(cTo, n);
		Object itemFrom = null;
		Object itemTo = null;
		Class<?> cFrom = null;
		for (int i = 0; i < n; i++) {
			itemFrom = Array.get(from, i);
			if (itemFrom == null) {
				continue;
			}
			cFrom = itemFrom.getClass();
			itemTo = getInstance(cFrom).esegui(itemFrom, new ClassPlus(cTo));
			Array.set(arrayTo, i, itemTo);
		}
		getMapped().put(from, arrayTo);

		return arrayTo;
	}

	@Override
	public final TypeObject getTipo() {
		return TypeObject.ARRAY;
	}
}

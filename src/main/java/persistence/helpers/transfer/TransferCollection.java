package persistence.helpers.transfer;

import java.lang.reflect.Type;
import java.util.Collection;

import persistence.helpers.bean.ClassPlus;
import persistence.helpers.bean.TransferType;

public class TransferCollection extends AbstractTransfer {

	public TransferCollection(final TransferFactory mappingFactory) {
		super(mappingFactory);
	}

	@Override
	public Object execute(final Object from, final ClassPlus plusTo) {
		final Class<?> classTo = plusTo.getClasse();
		if (isInitialized(from) && getMapped().containsKey(from)) {
			return super.getMapped().get(from);
		}

		if (getMapping().containsKey(from)) {
			return getMapping().get(from);
		}
		getMapping().put(from, from);

		if (!REFLECTION_HELPER.isCollection(classTo)) {
			return null;
		}

	/*
	 * Istanzio il corretto tipo di collection Se è istanziato un tipo
	 * specifico verrà usato quello altrimenti verrà scelta
	 * un'implementazione standard per le interfacce note
	 */
		final Collection itemsTo = (Collection) REFLECTION_HELPER.newInstance(classTo);
		final Collection itemsFrom = (Collection) from;

		if (itemsFrom.isEmpty()) {
			return itemsTo;
		}

		Type generic = plusTo.getGeneric();
		Class<?> cTo = null;
		if (generic == null) {
			cTo = REFLECTION_HELPER.wrappaGenerics(classTo);
		} else {
			cTo = REFLECTION_HELPER.wrappaGenerics(generic);
		}

		if (cTo == null) {
			return null;
		}
		Object itemTo = null;
		Class<?> cFrom = null;
		for (Object itemFrom : itemsFrom) {
			if (itemFrom == null) continue;
			cFrom = itemFrom.getClass();
			if (getTransferMetadata().getTransferType() == TransferType.RISTRETTO && verificaToMany(cFrom) && !isInitialized(itemFrom)) {
				return null;
			}

			itemTo = getInstance(cFrom).esegui(itemFrom, new ClassPlus(cTo));
			itemsTo.add(itemTo);
		}

		getMapped().put(from, itemsTo);
		return itemsTo;
	}

	@Override
	public final TypeObject getTipo() {
		return TypeObject.COLLECTION;
	}
}

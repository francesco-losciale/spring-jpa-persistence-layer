package commons.model.transfer;

import java.lang.reflect.Type;
import java.util.Map;

import commons.model.bean.ClassPlus;
import commons.model.bean.TransferType;

public class TransferMap extends AbstractTransfer {

	public TransferMap(final TransferFactory mappingFactory) {
		super(mappingFactory);
	}

	@Override
	public Object execute(final Object from, final ClassPlus plusTo) {
		final Class<?> classTo = plusTo.getClasse();
		if (isInitialized(from) && getMapped().containsKey(from)) {
			return super.getMapped().get(from);
		}

		if (getMapping().containsKey(from)) {
			return null;
		}
		getMapping().put(from, from);

		if (!REFLECTION_HELPER.isMap(classTo)) {
			return null;
		}

	/*
	 * Istanzio il corretto tipo di map Se è istanziato un tipo
	 * specifico verrà usato quello altrimenti verrà scelta
	 * un'implementazione standard per le interfacce note
	 */
		final Map mapTo = (Map) REFLECTION_HELPER.newInstance(classTo);
		final Map mapFrom = (Map) from;

		if (mapFrom.isEmpty()) {
			return mapTo;
		}

		final Type generic = plusTo.getGeneric();
		Class<?> cTo = null;
		if (generic == null) {
			cTo = REFLECTION_HELPER.wrappaGenerics(classTo);
		} else {
			cTo = REFLECTION_HELPER.wrappaGenerics(generic);
		}
		Object itemFrom = null;
		Class<?> cFrom = null;
		Object itemTo = null;
		for (Object key : mapFrom.keySet()) {
			itemFrom = mapFrom.get(key);
			if (itemFrom == null) {
				mapTo.put(key, null);
				continue;
			}
			cFrom = itemFrom.getClass();
			if (getTransferMetadata().getTransferType() == TransferType.RISTRETTO && verificaToMany(cFrom, cTo) && !isInitialized(itemFrom)) {
				return null;
			}

			itemTo = getInstance(cFrom).esegui(itemFrom, new ClassPlus(cTo));
			mapTo.put(key, itemTo);
		}

		getMapped().put(from, mapTo);
		return mapTo;
	}

	protected boolean verificaToMany(final Class<?> cFrom, final Class<?> cTo) {
		return (getAnnotatedClass(cTo) == cFrom);
	}

	@Override
	public final TypeObject getTipo() {
		return TypeObject.MAP;
	}
}

package persistence.helpers.transfer;

import java.lang.reflect.Type;
import java.util.List;

import persistence.helpers.bean.ClassPlus;

public class TransferObject extends AbstractTransfer {

	public TransferObject(final TransferFactory mappingFactory) {
		super(mappingFactory);
	}

	@Override
	public Object execute(final Object from, final ClassPlus plusTo) {
		final Class<?> classTo = plusTo.getClasse();
		if (super.getMapped().containsKey(from)) {
			return super.getMapped().get(from);
		}
//		if (super.getMapping().containsKey(from)) {
//			return null;
//		}

		super.getMapping().put(from, from);

		final List<String> props = getProperties(from, classTo);

		final Class<?> classFrom = from.getClass();

		final Object to = REFLECTION_HELPER.newInstance(classTo);
		Class<?> cFrom = null;
		Class<?> cTo = null;
		Type genericTo = null;
		Object value = null;
		ITransfer transfer = null;
		for (String key : props) {
			cFrom = REFLECTION_HELPER.getClasse(classFrom, key);
			cTo = REFLECTION_HELPER.getClasse(classTo, key);
			genericTo = REFLECTION_HELPER.getGeneric(classTo, key);

			if (salta(from, key, cFrom, cTo)) {
				continue;
			}
			value = REFLECTION_HELPER.get(from, key);
			transfer = super.getInstance(cFrom);
//	    System.out.println(value==null?"null":value.getClass()+">"+cTo);
//	    if(value!=null && value.getClass().getName().contains("javassist"))
//		System.out.println("!!!");
			REFLECTION_HELPER.copy(transfer.esegui(value, new ClassPlus(cTo, genericTo)), to, key);
		}

//	    if(to!=null && to.getClass().getName().contains("javassist"))
//		System.out.println("!!!");

		getMapped().put(from, to);
		return to;
	}

	@Override
	public final TypeObject getTipo() {
		return TypeObject.OBJECT;
	}
}

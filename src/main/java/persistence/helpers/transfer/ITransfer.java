package persistence.helpers.transfer;

import persistence.helpers.bean.ClassPlus;
import persistence.helpers.util.ReflectionHelper;

public interface ITransfer {
	public final ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();

	public Object esegui(Object from, ClassPlus plusTo);

	public Object esegui(Object from, Class<?> classTo);
}

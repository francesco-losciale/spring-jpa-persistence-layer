package persistence2.helpers.transfer;

import persistence2.helpers.bean.ClassPlus;
import persistence2.helpers.util.ReflectionHelper;

public interface ITransfer {
	public final ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();

	public Object esegui(Object from, ClassPlus plusTo);

	public Object esegui(Object from, Class<?> classTo);
}

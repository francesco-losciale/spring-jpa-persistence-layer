package commons.model.transfer;

import commons.model.bean.ClassPlus;
import commons.model.util.ReflectionHelper;

public interface ITransfer {
	public final ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();

	public Object esegui(Object from, ClassPlus plusTo);

	public Object esegui(Object from, Class<?> classTo);
}

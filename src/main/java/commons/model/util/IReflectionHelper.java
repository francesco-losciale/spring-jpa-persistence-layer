package commons.model.util;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public interface IReflectionHelper {

	public Object get(Object object, String key);

	public PropertyDescriptor getPropertyDescriptor(Object object, String key);

	public Class<?> getClasse(Class<?> cls, String key);

	public Object read(Object object, PropertyDescriptor pd);

	public boolean haveAnnotation(PropertyDescriptor pd, Class<? extends Annotation> annotation);

	public void copy(Object value, Object object, String key);

	public boolean isEqual(PropertyDescriptor descriptor1, PropertyDescriptor descriptor2);

	public Map<String, PropertyDescriptor> getStructure(Object bean);

	public Map<String, PropertyDescriptor> getStructure(Class<?> cls);

	public boolean isExtension(Class<?> cls, Class<?> parent);

	public boolean isCollection(Class<?> c);

	public boolean isSet(Class<?> c);

	public boolean isArray(Class<?> c);

	public boolean compatibili(Class<?> c, Class<?> classe);

	public Object newInstance(Class<?> cls);

	public Object newInstanceBase(Class<?> classe) throws Exception;

	public Object newInstanceCollection(Class<?> classe) throws Exception;

	public boolean isBase(Class<?> classe);

	public Class<?> wrappaGenerics(Method getMethod);

	public Class<?> wrappaGenerics(Class<?> cls);

	public boolean haveProperty(Object object, String key);

	public boolean haveProperty(Class<?> cls, String key);

	public <T> T cloneBean(T toClone);

	public Method getMethod(Class<?> clazz, String name);

	public List<String> getFields(Class<?> cls);
}
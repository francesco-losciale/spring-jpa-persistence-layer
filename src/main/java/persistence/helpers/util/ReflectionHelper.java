package persistence.helpers.util;

import org.apache.commons.beanutils.BeanUtils;
import org.ho.yaml.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import persistence.helpers.exception.ReflectionException;
import persistence.helpers.exception.ReflectionParserException;
import persistence.helpers.exception.TransferException;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


/**
 * @author Riccardo Fedel (riccardo.fedel@finconsgroup.com)
 */

public class ReflectionHelper implements IReflectionHelper {

	private static Logger LOG = LoggerFactory.getLogger(ReflectionHelper.class);

	private static Map<Class<?>, Map<String, PropertyDescriptor>> repository = new HashMap<Class<?>, Map<String, PropertyDescriptor>>();

	public Object get(final Object object, final String key) {

		if (object == null) {
			return null;
		}

		try {
			return ReflectionParserBuilder.getPropertyValue(object, key);
		} catch (ReflectionParserException e) {
			LOG.error("si è verificato un errore durante il parsing dell'oggetto");
		}

		return null;
	}

	public PropertyDescriptor getPropertyDescriptor(final Class<?> cls, final String key) {
		return getStructure(cls).get(key);
	}

	public PropertyDescriptor getPropertyDescriptor(final Object object, final String key) {

		if (object == null) {
			return null;
		}

		try {
			return ReflectionParserBuilder.getPropertyDescription(object, key);
		} catch (ReflectionParserException e) {
			LOG.error("si è verificato un errore durante il parsing dell'oggetto");
		}
		return null;
	}

	public Class<?> getClasse(final Class<?> cls, final String key) {
		if (cls == null) {
			return null;
		}
		if (key.contains(".")) {
			final int k = key.indexOf('.');
			final String f = key.substring(0, k);
			final String key1 = key.substring(k + 1);
			final Class<?> c = getClasse(cls, f);
			return getClasse(c, key1);
		} else {
			final PropertyDescriptor pd = getPropertyDescriptor(cls, key);
			if (pd == null) {
				return null;
			}
			return pd.getReadMethod().getReturnType();
		}
	}

	public Object read(final Object object, final PropertyDescriptor pd) {
		if (pd == null) {
			return null;
		}
		try {
			return pd.getReadMethod().invoke(object, (Object[]) null);
		} catch (Exception e) {
			LOG.error("Read", e);
			return null;
		}
	}

	public boolean haveAnnotation(final PropertyDescriptor pd, final Class<? extends Annotation> annotation) {
		return pd.getReadMethod().getAnnotation(annotation) != null;
	}

	public void copy(final Object value, final Object object, final String key) {
		try {
			ReflectionUtil.setProperty(object, key, value);
		} catch (Exception e) {
			throw new ReflectionException(e);
		}
	}

	public boolean isEqual(final PropertyDescriptor f1, final PropertyDescriptor f2) {
		return isEqual(f1.getPropertyType(), f2.getPropertyType());
	}

	public boolean isEqual(final Class<?> cls1, final Class<?> cls2) {
		return ReflectionUtil.isCastableFrom(cls1, cls2) || ReflectionUtil.isCastableFrom(cls2, cls1);
	}

	public Map<String, PropertyDescriptor> getStructure(final Object bean) {
		return getStructure(bean.getClass());
	}

	public Map<String, PropertyDescriptor> getStructure(final Class<?> cls) {
		Map<String, PropertyDescriptor> map = repository.get(cls);
		if (map != null) {
			return map;
		}
		map = createMap(cls);
		addMap(cls, map);
		return map;
	}

	private Map<String, PropertyDescriptor> createMap(final Class<?> cls) {
		final HashMap<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
		final List<PropertyDescriptor> pds = ReflectionUtil.getProperties(cls);
		if (pds != null && !pds.isEmpty()) {
			for (PropertyDescriptor pd : pds) {
				if (pd.getReadMethod() != null && pd.getWriteMethod() != null && pd.getWriteMethod().getParameterTypes() != null && pd.getWriteMethod().getParameterTypes().length == 1 && pd.getReadMethod().getReturnType() != null && pd.getReadMethod().getReturnType() == pd.getWriteMethod().getParameterTypes()[0]) {
					map.put(pd.getName(), pd);
				}
			}
		}
		return map;
	}

	private void addMap(Class<?> cls, Map<String, PropertyDescriptor> map) {
		if (!repository.containsKey(cls)) {
			repository.put(cls, map);
		}
	}

	public boolean isExtension(Class<?> cls, Class<?> parent) {

		Class<?> _class = cls;

		while (_class.getSuperclass() != null && _class != _class.getSuperclass()) {

			if (_class.equals(parent)) {
				return true;
			}
			if (parent.isInterface()) {
				final Class<?>[] interfaces = _class.getInterfaces();
				if (interfaces != null && interfaces.length > 0) {
					for (Class<?> inter : interfaces) {
						if (inter == parent) {
							return true;
						}
					}
				}
			}
			_class = _class.getSuperclass();
		}

		return false;
	}

	public boolean isCollection(Class<?> c) {
		return compatibili(c, java.util.List.class) || compatibili(c, java.util.Collection.class);
	}

	public boolean isSet(Class<?> c) {
		return compatibili(c, java.util.Set.class);
	}

	public boolean isMap(Class<?> c) {
		return compatibili(c, java.util.Map.class);
	}

	public boolean isArray(Class<?> c) {
		if (c == null) {
			return false;
		}

		return c.isArray();
	}

	public boolean compatibili(Class<?> c, Class<?> classe) {
		if (c == null) {
			return false;
		}
	/*
	 * La modalità precedente non risaliva la gerarchia della classe e delle
	 * sue interfacce
	 */
		return classe.isAssignableFrom(c);
	}

	public Object newInstance(Class<?> cls) throws TransferException {
		try {
			return newInstanceBase(cls);
		} catch (Exception e) {
			throw new TransferException("La classe " + cls + " non ha costruttore vuoto");
		}
	}

	public final Object newInstanceBase(Class<?> classe) throws Exception {
		if (isCollection(classe)) {
			return newInstanceCollection(classe);
		} else if (isMap(classe)) {
			return newInstanceMap(classe);
		} else if (classe == java.lang.Character.class || classe.getName().equals("char")) {
			return new Character(' ');
		} else if (classe == java.lang.Short.class || classe.getName().equals("short")) {
			return Short.valueOf((short) 0);
		} else if (classe == java.lang.Boolean.class || classe.getName().equals("boolean")) {
			return Boolean.FALSE;
		} else if (classe == java.lang.Long.class || classe.getName().equals("long")) {
			return Long.valueOf(0l);
		} else if (classe == java.lang.Double.class || classe.getName().equals("double")) {
			return new Double(0);
		} else if (classe == java.lang.Float.class || classe.getName().equals("float")) {
			return new Float(0);
		} else if (classe == java.lang.Integer.class || classe.getName().equals("int")) {
			return Integer.valueOf(0);
		} else if (classe == java.lang.Byte.class || classe.getName().equals("byte")) {
			return new Byte("");
		} else if (classe == java.lang.String.class) {
			return new java.lang.String();
		} else if (classe == java.math.BigDecimal.class) {
			return BigDecimal.ZERO;
		} else if (classe == java.math.BigInteger.class) {
			return BigInteger.ZERO;
		} else if (classe == Calendar.class || classe == GregorianCalendar.class) {
			return GregorianCalendar.getInstance();
		} else if (classe == java.sql.Date.class) {
			return new java.sql.Date(System.currentTimeMillis());
		} else if (classe == java.sql.Time.class) {
			return new java.sql.Time(System.currentTimeMillis());
		} else if (classe == java.sql.Timestamp.class) {
			return new java.sql.Timestamp(System.currentTimeMillis());
		}
		return classe.newInstance();
	}

	@SuppressWarnings("unchecked")
	public Object newInstanceCollection(Class<?> classe) throws Exception {
		// Se non è un'interfaccia, lo instanzio
		if (!classe.isInterface()) {
			return classe.newInstance();
		}
		// Se è un'interfaccia stabilisco delle implementazioni di default
		if (classe == java.util.SortedSet.class) {
			return new java.util.TreeSet();
		}
		if (classe == java.util.Set.class) {
			return new java.util.HashSet();
		}
		if (classe == java.util.List.class) {
			return new java.util.ArrayList();
		}
		if (classe == java.util.Collection.class) {
			return new java.util.ArrayList();
		}
		throw new Exception("Collection non supportata: " + classe.getName());
	}

	@SuppressWarnings("unchecked")
	public Object newInstanceMap(Class<?> classe) throws Exception {
		// Se non è un'interfaccia, lo instanzio
		if (!classe.isInterface()) {
			return classe.newInstance();
		}
		// Se è un'interfaccia stabilisco delle implementazioni di default
		if (classe == java.util.Map.class) {
			return new java.util.TreeMap();
		}
		throw new Exception("Map non supportata: " + classe.getName());
	}

	public final boolean isBase(final Class<?> classe) {
		if (classe.isPrimitive()) {
			return true;
		} else if (classe == java.lang.Character.class || classe.getName().equals("char")) {
			return true;
		} else if (classe == java.lang.Short.class || classe.getName().equals("short")) {
			return true;
		} else if (classe == java.lang.Boolean.class || classe.getName().equals("boolean")) {
			return true;
		} else if (classe == java.lang.Long.class || classe.getName().equals("long")) {
			return true;
		} else if (classe == java.lang.Double.class || classe.getName().equals("double")) {
			return true;
		} else if (classe == java.lang.Float.class || classe.getName().equals("float")) {
			return true;
		} else if (classe == java.lang.Integer.class || classe.getName().equals("int")) {
			return true;
		} else if (classe == java.lang.Byte.class || classe.getName().equals("byte")) {
			return true;
		} else if (classe == java.lang.String.class) {
			return true;
		} else if (classe == java.math.BigDecimal.class) {
			return true;
		} else if (classe == java.math.BigInteger.class) {
			return true;
		} else if (classe == Calendar.class || classe == GregorianCalendar.class) {
			return true;
		} else if (classe == java.util.Date.class) {
			return true;
		} else if (classe == java.sql.Date.class) {
			return true;
		} else if (classe == java.sql.Time.class) {
			return true;
		} else if (classe == java.sql.Timestamp.class) {
			return true;
		}
		return false;
	}

	public Class<?> wrappaGenerics(Method getMethod) {
		Class<?> cls = null;
		Type type = getMethod.getGenericReturnType();
		if (type != null) {
			cls = wrappaGenerics(type);
		}
		if (cls != null) {
			return cls;
		}
		final Type[] types = getMethod.getGenericParameterTypes();
		if (types != null && types.length > 0) {
			type = getMethod.getGenericParameterTypes()[0];
		}
		if (type != null) {
			return wrappaGenerics(type);
		}
		return null;
	}

	public Class<?> wrappaGenerics(Class<?> cls) {
		final Type type = cls.getGenericSuperclass();
		return wrappaGenerics(type);
	}

	public Class<?> wrappaGenerics(Type type) {
		try {
			if (type instanceof ParameterizedType) {
				final Type[] ar = ((ParameterizedType) type).getActualTypeArguments();
				final int k = ar == null ? -1 : ar.length - 1;
				if (k >= 0 && ar[k] instanceof Class) {
					return (Class<?>) ar[k];
				}
			} else {
				return (Class<?>) type;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public boolean haveProperty(Object object, String key) {
		final PropertyDescriptor pd = getPropertyDescriptor(object.getClass(), key);
		return pd != null;
	}

	public boolean haveProperty(Class<?> cls, String key) {
		final PropertyDescriptor pd = getPropertyDescriptor(cls, key);
		return pd != null;
	}

	public <T> T cloneBean(T toClone) {
		try {
			return (T) BeanUtils.cloneBean(toClone);
		} catch (IllegalAccessException e) {
			throw new TransferException(e);
		} catch (InstantiationException e) {
			throw new TransferException(e);
		} catch (InvocationTargetException e) {
			throw new TransferException(e);
		} catch (NoSuchMethodException e) {
			throw new TransferException(e);
		}
	}

	public Method getMethod(Class<?> clazz, String name) {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(name, null);
		} catch (NoSuchMethodException e) {
			throw new TransferException(e);
		}
		return method;
	}

	public List<String> getFields(final Class<?> cls) {
		final List<String> fields = new ArrayList<String>();
		final Map<String, PropertyDescriptor> struct = getStructure(cls);
		for (String key : struct.keySet()) {
			fields.add(key);
		}
		return fields;
	}

	public Type getGeneric(Class<?> cls, String key) {
		final PropertyDescriptor pd = getPropertyDescriptor(cls, key);
		final Type[] types = pd.getWriteMethod().getGenericParameterTypes();
		if (types == null || types.length == 0) {
			return null;
		}
		// System.out.println(types[0]);
		return types[0];
	}
}
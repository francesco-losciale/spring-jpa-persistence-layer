package persistence.helpers.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import persistence.helpers.bean.ClassPlus;
import persistence.helpers.bean.EntityMapped;
import persistence.helpers.bean.TransferMetadata;
import persistence.helpers.bean.TransferType;
import persistence.helpers.util.IReflectionHelper;
import persistence.helpers.util.ReflectionHelper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public abstract class AbstractTransfer implements ITransfer {

	private static final Logger log = LoggerFactory.getLogger(AbstractTransfer.class);
	private static Method methodIsInitialized;
	private final TransferFactory transferFactory;

	public AbstractTransfer(final TransferFactory mappingFactory) {
		this.transferFactory = mappingFactory;
	}

	public Object esegui(final Object from, final Class<?> classTo) {
		return esegui(from, new ClassPlus(classTo));
	}

	public Object esegui(final Object from, final ClassPlus plusTo) {
		if (from == null) {
			return null;
		}
		if (from.getClass() == plusTo.getClasse() && !REFLECTION_HELPER.isCollection(from.getClass()) && !REFLECTION_HELPER.isMap(from.getClass())) {
			return from;
		}
		return execute(from, plusTo);
	}

	public abstract Object execute(Object from, ClassPlus plusTo);

	public abstract TypeObject getTipo();

	protected List<String> getProperties(final Object from, final Class<?> classTo) {

		final List<String> lista = new ArrayList<String>();
		if (from == null) {
			return lista;
		}

		Set<String> properties = getTransferMetadata().getProperties();
		TransferType transferType = getTransferMetadata().getTransferType();

		final boolean haveIncludeProperty = transferType == TransferType.INCLUDI || transferType == TransferType.INCLUDI_E_VISTA;

		final boolean haveExcludeProperty = transferType == TransferType.ESCLUDI || transferType == TransferType.ESCLUDI_E_VISTA;

		final boolean isSimpleTransfer = transferType == TransferType.SEMPLICE;

		if (properties == null) {
			properties = new HashSet<String>();
		}

		final Map<String, PropertyDescriptor> mapFrom = REFLECTION_HELPER.getStructure(from);
		final Map<String, PropertyDescriptor> mapTo = REFLECTION_HELPER.getStructure(classTo);

		if (mapTo == null || mapTo.isEmpty() || mapFrom == null || mapFrom.isEmpty()) {
			return lista;
		}

		IReflectionHelper reflectionHelper = new ReflectionHelper();
		for (String key : mapFrom.keySet()) {

			// verifico se ho deciso di skippare questa proprietà
			if (haveIncludeProperty && !properties.contains(key) && getTipoDaControllare(getTipo())) {
				continue;
			}

			if (haveExcludeProperty && properties.contains(key)) {
				continue;
			}

			PropertyDescriptor propertyTo = mapTo.get(key);
			if (propertyTo == null) {
				continue;
			}

			Class<?> clazz = propertyTo.getPropertyType();
			if (isSimpleTransfer && !reflectionHelper.isBase(clazz)) {
				continue;
			}

			lista.add(key); 
		}
		return lista;
	}

	private boolean getTipoDaControllare(TypeObject tipo) {
		return tipo != TypeObject.SIMPLE && tipo != TypeObject.OBJECT;
	}

	protected TransferMetadata getTransferMetadata() {
		TransferMetadata map = getTransferFactory().getSessionMapping().getMappingMetadata();
		if (map == null) {
			map = TransferMetadata.DEFAULT;
		}
		return map;
	}

	protected TransferFactory getTransferFactory() {
		return transferFactory;
	}

	protected Map<Object, Object> getMapped() {
		return getTransferFactory().getSessionMapping().getMapped();
	}

	protected Map<Object, Object> getMapping() {
		return getTransferFactory().getSessionMapping().getMapping();
	}

	protected ITransfer getInstance(Class<?> cFrom) {
		return getTransferFactory().getInstance(cFrom);
	}

	public Class<?> getAnnotatedClass(Class<?> cls) {
		if (cls == null) return null;
		EntityMapped annotation = cls.getAnnotation(EntityMapped.class);
		if (annotation == null) return null;
		try {
			return Class.forName(annotation.value());
		} catch (ClassNotFoundException e) {
			log.error("getMapped:" + cls.getName() + ":" + annotation.value(), e);
		}
		return null;
	}

	protected boolean isInitialized(Object valuePropertyFrom) {
		try {
			if (methodIsInitialized == null) {
				Class<?> classHibernate = Class.forName("org.hibernate.Hibernate");
				methodIsInitialized = classHibernate.getDeclaredMethod("isInitialized", Object.class);
			}
			Boolean iniziatized = (Boolean) methodIsInitialized.invoke(null, valuePropertyFrom);
			return iniziatized.booleanValue();
		} catch (Exception e) {
			log.error("isInitialized", e);
		}
		return false;
	}

	protected boolean salta(Object from, String property, Class<?> cFrom, Class<?> cTo) {
		if (!REFLECTION_HELPER.isCollection(cFrom) && !REFLECTION_HELPER.isSet(cFrom)) return false;
		Field field = null;
		Object itemFrom = null;

		if (getTransferMetadata().getTransferType() != TransferType.RISTRETTO) return false;
		try {
			Class<?> c = from.getClass();
			if (verificaToMany(c)) return true;
			field = from.getClass().getDeclaredField(property);
			while (field == null && c != null) {
				c = c.getSuperclass();
				if (c != null) field = c.getDeclaredField(property);
			}
			if (field == null) return false;
			field.setAccessible(true);
			itemFrom = field.get(from);
		} catch (Exception e) {
			log.error("transfer.salta: from:={}; property:={}; cFrom:={}; cTo:={}; e:={}.", from, property /* cFrom, cTo, e ::TODO::upgrade sl4j */ );
		}
		if (itemFrom == null) return true;
		if (verificaToMany(itemFrom.getClass())
			// && !isInitialized(itemFrom)
				) return true;
		return false;
	}

	protected boolean verificaToMany(Class<?> classFrom) {
		return false; //return classFrom.getName().contains("hibernate") || classFrom.getName().contains("javassist");
	}
}

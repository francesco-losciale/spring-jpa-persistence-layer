package persistence.helpers.util;

public class ElementInfo {

	private Object target;
	private Class<?> targetClass;
	private String key;
	private ElementType elementType;

	public ElementInfo(Object target, String key) {
		super();
		this.target = target;
		this.key = key;

		this.elementType = ElementType.UNKNOWN;
		// se target è NULL non posso definire il target
		if (target == null) {
			return;
		}

		targetClass = target.getClass();
		this.elementType = ElementType.PROPERTY;

		//verifico nel caso sia una mappa o una lista
		if (Utils.verifyRegExMap(key) || Utils.verifyRegExCollection(key)) {
			this.elementType = ElementType.MAP_OR_COLLECTION;
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<?> parentClass) {
		this.targetClass = parentClass;
	}

	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

	@Override
	public String toString() {
		return targetClass + ":" + key;
	}

	public enum ElementType {
		PROPERTY, MAP_OR_COLLECTION, UNKNOWN;
	}
}

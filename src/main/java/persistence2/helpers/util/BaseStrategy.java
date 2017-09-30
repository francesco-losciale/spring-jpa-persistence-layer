package persistence2.helpers.util;

import java.beans.PropertyDescriptor;

import persistence2.helpers.exception.ReflectionParserException;
import persistence2.helpers.util.ElementInfo.ElementType;

public abstract class BaseStrategy implements IStrategy {

	private static final ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();

	public Result fire(ElementInfo element) throws ReflectionParserException {

		// verifico le pre-condizioni
		if (element == null) {
			return Result.NO_RESULT;
		}
		if (ElementType.UNKNOWN.equals(element.getElementType())) {
			return Result.NO_RESULT;
		}

		// valori di ritorno
		Object value = null;
		PropertyDescriptor propertyDescriptor = null;
		// variabili di supporto
		String key = element.getKey();
		Object target = element.getTarget();
		Class<?> targetClass = element.getTargetClass();

		if (ElementType.PROPERTY.equals(element.getElementType())) {
			// descrittore
			propertyDescriptor = REFLECTION_HELPER.getPropertyDescriptor(targetClass, key);
			// leggo valore
			value = REFLECTION_HELPER.read(target, propertyDescriptor);
		} else if (ElementType.MAP_OR_COLLECTION.equals(element.getElementType())) {
			int startPar = key.indexOf("[");

			String property = key.substring(0, startPar);

			propertyDescriptor = REFLECTION_HELPER.getPropertyDescriptor(targetClass, property);

			value = REFLECTION_HELPER.read(target, propertyDescriptor);
			Class<?> valueClass = value.getClass();

			if (REFLECTION_HELPER.isMap(valueClass)) {
				int endPar = key.lastIndexOf("']");
				String index = key.substring(startPar + 2, endPar);
				value = Utils.getByPositionForMap(value, index);
			} else if (REFLECTION_HELPER.isCollection(valueClass) || REFLECTION_HELPER.isSet(valueClass) || REFLECTION_HELPER.isArray(valueClass)) {
				int endPar = key.lastIndexOf("]");
				String index = key.substring(startPar + 1, endPar);
				value = Utils.getByPositionForCollection(value, Integer.parseInt(index));
			} else {
				throw new ReflectionParserException("Il valore della classe non risulta essere di tipo Map o Collection o array");
			}
		}

		if (this instanceof PropertyValueStrategy) return new Result(value, value);
		else if (this instanceof PropertyDescriptorStrategy) return new Result(value, propertyDescriptor);

		return null;
	}

	/**
	 * genero l'elemento in funzione del tipo
	 */
	public ElementInfo generateElement(Object target, String key) {
		ElementInfo element = new ElementInfo(target, key);

		return element;
	}
}

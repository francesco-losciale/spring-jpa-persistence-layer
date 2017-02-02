package commons.model.util;

import commons.model.exception.ReflectionParserException;

interface IStrategy {

	public ElementInfo generateElement(Object target, String key);

	public Result fire(ElementInfo element) throws ReflectionParserException;
}

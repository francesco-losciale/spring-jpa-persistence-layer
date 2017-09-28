package persistence.helpers.util;

import persistence.helpers.exception.ReflectionParserException;

interface IStrategy {

	public ElementInfo generateElement(Object target, String key);

	public Result fire(ElementInfo element) throws ReflectionParserException;
}

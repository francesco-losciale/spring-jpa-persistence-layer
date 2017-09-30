package persistence2.helpers.util;

import persistence2.helpers.exception.ReflectionParserException;

interface IStrategy {

	public ElementInfo generateElement(Object target, String key);

	public Result fire(ElementInfo element) throws ReflectionParserException;
}

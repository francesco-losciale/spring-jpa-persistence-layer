package persistence.helpers.util;

import persistence.helpers.exception.ReflectionParserException;

public interface IReflectionParser {

	public Object parse(Object target, String string) throws ReflectionParserException;
}

package persistence2.helpers.util;

import persistence2.helpers.exception.ReflectionParserException;

public interface IReflectionParser {

	public Object parse(Object target, String string) throws ReflectionParserException;
}

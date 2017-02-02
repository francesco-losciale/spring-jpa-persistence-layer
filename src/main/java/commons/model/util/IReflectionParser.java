package commons.model.util;

import commons.model.exception.ReflectionParserException;

public interface IReflectionParser {

	public Object parse(Object target, String string) throws ReflectionParserException;
}

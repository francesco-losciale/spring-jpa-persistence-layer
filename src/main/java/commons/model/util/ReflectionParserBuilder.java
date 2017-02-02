package commons.model.util;

import java.beans.PropertyDescriptor;

import commons.model.exception.ReflectionParserException;

public class ReflectionParserBuilder {

	public static PropertyDescriptor getPropertyDescription(final Object target, final String string) throws ReflectionParserException {

		final IStrategy strategy = new PropertyDescriptorStrategy();
		final IReflectionParser parser = new ReflectionParser(strategy);

		return (PropertyDescriptor) parser.parse(target, string);
	}

	public static Object getPropertyValue(final Object target, final String string) throws ReflectionParserException {

		final IStrategy strategy = new PropertyValueStrategy();
		final IReflectionParser parser = new ReflectionParser(strategy);

		return parser.parse(target, string);
	}
}

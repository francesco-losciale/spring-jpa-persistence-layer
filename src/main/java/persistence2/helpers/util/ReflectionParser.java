package persistence2.helpers.util;

import java.util.StringTokenizer;

import persistence2.helpers.exception.ParserException;
import persistence2.helpers.exception.ReflectionParserException;

public class ReflectionParser implements IReflectionParser {

	private final IStrategy strategy;

	public ReflectionParser(final IStrategy strategy) {
		this.strategy = strategy;
	}

	public Object parse(final Object obj, final String string) throws ReflectionParserException {

		final StringTokenizer tokenizer = new StringTokenizer(string, ".");

		if (tokenizer.countTokens() == 0) {
			throw new ParserException("Nessun token trovato");
		}

		Object target = obj;
		Object result = null;
		String token = null;
		ElementInfo info = null;
		Result tmp = null;
		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();

			info = strategy.generateElement(target, token);
			tmp = strategy.fire(info);
			// verifico il caso in cui non sia possibile recuperare il valore
			if (tmp == Result.NO_RESULT) {
				return null;
			}

			result = tmp.getResult();
			target = tmp.getValue();
		}

		return result;
	}
}

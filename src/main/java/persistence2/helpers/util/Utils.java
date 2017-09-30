package persistence2.helpers.util;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import persistence2.helpers.exception.ParserException;

class Utils {

	@SuppressWarnings("unchecked")
	public static Object getByPositionForMap(final Object target, final Object key) {
		return ((Map) target).get(key);
	}

	public static Object getByPositionForCollection(final Object target, final int position) {

		Object value = null;

		if (target.getClass().isArray()) {
			value = Array.get(target, position);
		} else if (target instanceof List<?>) {

			final List<?> list = (List<?>) target;
			value = list.get(position);
		} else if (target instanceof Set<?>) {

			final Set<?> set = (Set<?>) target;
			if (position < 0 || position >= set.size()) {
				throw new ParserException("position  <0 o >= set.size");
			}
			final Iterator<?> it = set.iterator();
			Object elem = null;
			for (int j = 0; it.hasNext(); j++) {
				elem = it.next();
				if (j == position) {
					value = elem;
					break;
				}
			}
		}

		return value;
	}

	/**
	 * map[1]
	 *
	 * @param key
	 * @return
	 */
	public static boolean verifyRegExMap(final String key) {
		return Utils.find(key, "\\[\\'(\\w)*\\'\\]");
	}

	/**
	 * set[0]
	 *
	 * @param key
	 * @return
	 */
	public static boolean verifyRegExCollection(final String key) {
		return Utils.find(key, "\\[(\\w)*\\]");
	}

	public static boolean find(final String key, final String exPattern) {

		final Pattern pattern = Pattern.compile(exPattern);

		final Matcher matcher = pattern.matcher(key);
		return matcher.find();
	}
}

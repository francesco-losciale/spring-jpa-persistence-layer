package commons.cache;

import java.util.HashMap;

public class ThreadCacheCapability extends ThreadLocal<HashMap<String, ICacheable>> implements IThreadCache {

	@Override
	protected synchronized HashMap<String, ICacheable> initialValue() {
		return new HashMap<String, ICacheable>();
	}

	public synchronized boolean put(String key, ICacheable value) {

		HashMap<String, ICacheable> threadCache = get();

		ICacheable previus = threadCache.put(key, value);
		set(threadCache);

		return previus == null;
	}

	public synchronized boolean put(ICacheable value) {

		return put(value.getUniqueKey(), value);
	}

	public synchronized ICacheable get(String key) {

		HashMap<String, ICacheable> threadCache = get();

		return threadCache.get(key);
	}

	public synchronized void clear() {
		set(new HashMap<String, ICacheable>());
	}

	public int size() {
		HashMap<String, ICacheable> threadCache = get();
		return threadCache.size();
	}
}

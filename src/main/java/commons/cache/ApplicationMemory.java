package commons.cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ApplicationMemory implements IThreadCache {

	public static final String NAME = "applicationMemory";

	private static final IThreadCache threadLocal = new ThreadCacheCapability();

	public boolean put(String key, ICacheable value) {
		return threadLocal.put(key, value);
	}

	public void clear() {
		threadLocal.clear();
	}

	public ICacheable get(String key) {
		return threadLocal.get(key);
	}

	public int size() {
		return threadLocal.size();
	}

	public boolean put(ICacheable value) {
		return threadLocal.put(value);
	}
}

package manager.cache;

public interface IThreadCache {

	public boolean put(String key, ICacheable value);

	public boolean put(ICacheable value);

	public ICacheable get(String key);

	public void clear();

	public int size();
}

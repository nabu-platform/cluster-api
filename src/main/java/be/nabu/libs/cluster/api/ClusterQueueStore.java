package be.nabu.libs.cluster.api;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ClusterQueueStore<T> {
	public void store(Long key, T value);
	public void storeAll(Map<Long, T> map);

	public void delete(Long key);
	public void deleteAll(Collection<Long> keys);

	public Set<Long> loadAllKeys();
	public T load(Long key);
	public Map<Long, T> loadAll(Collection<Long> keys);
}

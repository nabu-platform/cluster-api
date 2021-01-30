package be.nabu.libs.cluster.api;

import java.util.List;

public interface ClusterInstance {
	public ClusterLock lock(String name);
	public ClusterAtomicLong atomicLong(String name);
	public ClusterCountDownLatch countDownLatch(String name);
	public ClusterIdGenerator idGenerator(String name);
	public <K, V> ClusterMap<K, V> map(String name);
	public <T> ClusterSet<T> set(String name);
	public <T> ClusterList<T> list(String name);
	public <T> ClusterBlockingQueue<T> queue(String name);
	public <T> ClusterTopic<T> topic(String name);
	public List<ClusterMember> members();
}

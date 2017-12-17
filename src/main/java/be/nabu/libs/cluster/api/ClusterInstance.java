package be.nabu.libs.cluster.api;

public interface ClusterInstance {
	public ClusterLock lock(String name);
	public ClusterAtomicLong atomicLong(String name);
	public ClusterCountDownLatch countDownLatch(String name);
}

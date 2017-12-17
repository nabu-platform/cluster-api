package be.nabu.libs.cluster.api;

public interface ClusterAtomicLong {
	public long addAndGet(long delta);
	public boolean compareAndSet(long expect, long update);
	public long decrementAndGet();
	public long get();
	public long getAndAdd(long delta);
	public long getAndDecrement();
	public long getAndIncrement();
	public long getAndSet(long newValue);
	public long incrementAndGet();
	public void lazySet(long newValue);
	public void set(long newValue);
}

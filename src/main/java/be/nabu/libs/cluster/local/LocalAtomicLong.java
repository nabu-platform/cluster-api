package be.nabu.libs.cluster.local;

import java.util.concurrent.atomic.AtomicLong;

import be.nabu.libs.cluster.api.ClusterAtomicLong;

public class LocalAtomicLong implements ClusterAtomicLong {

	private AtomicLong atomic = new AtomicLong();
	
	@Override
	public long addAndGet(long delta) {
		return atomic.addAndGet(delta);
	}

	@Override
	public boolean compareAndSet(long expect, long update) {
		return atomic.compareAndSet(expect, update);
	}

	@Override
	public long decrementAndGet() {
		return atomic.decrementAndGet();
	}

	@Override
	public long get() {
		return atomic.get();
	}

	@Override
	public long getAndAdd(long delta) {
		return atomic.getAndAdd(delta);
	}

	@Override
	public long getAndDecrement() {
		return atomic.getAndDecrement();
	}

	@Override
	public long getAndIncrement() {
		return atomic.getAndIncrement();
	}

	@Override
	public long getAndSet(long newValue) {
		return atomic.getAndSet(newValue);
	}

	@Override
	public long incrementAndGet() {
		return atomic.incrementAndGet();
	}

	@Override
	public void lazySet(long newValue) {
		atomic.lazySet(newValue);
	}

	@Override
	public void set(long newValue) {
		atomic.set(newValue);
	}
	
	@Override
	public String toString() {
		return atomic.toString();
	}

}

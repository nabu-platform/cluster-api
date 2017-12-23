package be.nabu.libs.cluster.local;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import be.nabu.libs.cluster.api.ClusterLock;

public class LocalLock implements ClusterLock {

	private ReentrantLock lock;
	private LocalInstance localInstance;
	private String name;
	
	public LocalLock(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public void lock() {
		lock.lock();
	}

	@Override
	public void lock(long leaseTime, TimeUnit leaseTimeUnit) {
		this.lock();
	}

	@Override
	public void unlock() {
		lock.unlock();
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit timeoutUnit) throws InterruptedException {
		return lock.tryLock(timeout, timeoutUnit);
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit timeoutUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException {
		return this.tryLock(timeout, timeoutUnit);
	}

	@Override
	public boolean isLocked() {
		return lock.isLocked();
	}

	@Override
	public boolean tryLock() {
		return lock.tryLock();
	}

	@Override
	public String toString() {
		return lock.toString();
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.locks);
	}
}

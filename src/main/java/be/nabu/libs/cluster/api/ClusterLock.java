package be.nabu.libs.cluster.api;

import java.util.concurrent.TimeUnit;

public interface ClusterLock extends Destroyable {
	// lock indefinitely until explicitly unlocked
	// note that this may wait indefinitely as well if someone else has the lock
	public void lock();
	// lock for max this time or until unlocked earlier
	public void lock(long leaseTime, TimeUnit leaseTimeUnit);
	// release the lock
	public void unlock();
	// try to get a lock non-blocking
	public boolean tryLock();
	// try to get a lock and block for a given time
	public boolean tryLock(long timeout, TimeUnit timeoutUnit) throws InterruptedException;
	// with fixed lease time
	public boolean tryLock(long timeout, TimeUnit timeoutUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException;
	// whether or not the lock is currently locked
	public boolean isLocked();
}

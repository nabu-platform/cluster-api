package be.nabu.libs.cluster.api;

import java.util.concurrent.TimeUnit;

public interface ClusterCountDownLatch extends Destroyable {
	public void await() throws InterruptedException;
	public boolean await(long timeout, TimeUnit timeUnit) throws InterruptedException;
	public void countDown();
	public long getCount();
	// only when 0
	public boolean trySetCount(int count);
}

package be.nabu.libs.cluster.local;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import be.nabu.libs.cluster.api.ClusterCountDownLatch;

public class LocalCountDownLatch implements ClusterCountDownLatch {

	private CountDownLatch latch;
	
	@Override
	public void await() throws InterruptedException {
		getLatch().await();
	}

	@Override
	public boolean await(long timeout, TimeUnit timeUnit) throws InterruptedException {
		return getLatch().await(timeout, timeUnit);
	}

	@Override
	public void countDown() {
		getLatch().countDown();
	}

	@Override
	public long getCount() {
		return getLatch().getCount();
	}

	private CountDownLatch getLatch() {
		// we default to 0 as hazelcast does this too
		if (latch == null) {
			latch = new CountDownLatch(0);
		}
		return latch;
	}

	@Override
	public boolean trySetCount(int count) {
		if (getCount() == 0) {
			latch = new CountDownLatch(count);
			return true;
		}
		else {
			return false;
		}
	}
}

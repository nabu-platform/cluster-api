package be.nabu.libs.cluster.local;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import be.nabu.libs.cluster.api.ClusterAtomicLong;
import be.nabu.libs.cluster.api.ClusterCountDownLatch;
import be.nabu.libs.cluster.api.ClusterInstance;
import be.nabu.libs.cluster.api.ClusterLock;

public class LocalInstance implements ClusterInstance {

	private Map<String, WeakReference<ClusterLock>> locks = new HashMap<String, WeakReference<ClusterLock>>();
	private Map<String, WeakReference<ClusterAtomicLong>> atomicLongs = new HashMap<String, WeakReference<ClusterAtomicLong>>();
	private Map<String, WeakReference<ClusterCountDownLatch>> countDownLatches = new HashMap<String, WeakReference<ClusterCountDownLatch>>();
	
	@Override
	public ClusterLock lock(String name) {
		return get(name, locks, new Callable<ClusterLock>() {
			@Override
			public ClusterLock call() throws Exception {
				return new LocalLock();
			}
		});
	}

	@Override
	public ClusterAtomicLong atomicLong(String name) {
		return get(name, atomicLongs, new Callable<ClusterAtomicLong>() {
			@Override
			public ClusterAtomicLong call() throws Exception {
				return new LocalAtomicLong();
			}
		});
	}

	private <T> T get(String name, Map<String, WeakReference<T>> map, Callable<T> generator) {
		T instance = map.containsKey(name) ? map.get(name).get() : null;
		if (instance == null) {
			synchronized(map) {
				instance = map.containsKey(name) ? map.get(name).get() : null;
				if (instance == null) {
					try {
						instance = generator.call();
						map.put(name, new WeakReference<T>(instance));
					}
					catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return instance;
	}

	@Override
	public ClusterCountDownLatch countDownLatch(String name) {
		return get(name, countDownLatches, new Callable<ClusterCountDownLatch>() {
			@Override
			public ClusterCountDownLatch call() throws Exception {
				return new LocalCountDownLatch();
			}
		});
	}
}

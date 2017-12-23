package be.nabu.libs.cluster.local;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import be.nabu.libs.cluster.api.ClusterAtomicLong;
import be.nabu.libs.cluster.api.ClusterBlockingQueue;
import be.nabu.libs.cluster.api.ClusterCountDownLatch;
import be.nabu.libs.cluster.api.ClusterIdGenerator;
import be.nabu.libs.cluster.api.ClusterInstance;
import be.nabu.libs.cluster.api.ClusterList;
import be.nabu.libs.cluster.api.ClusterLock;
import be.nabu.libs.cluster.api.ClusterMap;
import be.nabu.libs.cluster.api.ClusterSet;
import be.nabu.libs.cluster.api.ClusterTopic;

public class LocalInstance implements ClusterInstance {

	Map<String, ClusterLock> locks = new HashMap<String, ClusterLock>();
	Map<String, ClusterCountDownLatch> countDownLatches = new HashMap<String, ClusterCountDownLatch>();
	Map<String, ClusterAtomicLong> atomicLongs = new HashMap<String, ClusterAtomicLong>();
	Map<String, ClusterIdGenerator> idGenerators = new HashMap<String, ClusterIdGenerator>();
	Map<String, ClusterMap<?, ?>> maps = new HashMap<String, ClusterMap<?, ?>>();
	Map<String, ClusterBlockingQueue<?>> queues = new HashMap<String, ClusterBlockingQueue<?>>();
	Map<String, ClusterSet<?>> sets = new HashMap<String, ClusterSet<?>>();
	Map<String, ClusterList<?>> lists = new HashMap<String, ClusterList<?>>();
	Map<String, ClusterTopic<?>> topics = new HashMap<String, ClusterTopic<?>>();
	
	@Override
	public ClusterLock lock(final String name) {
		return get(name, locks, new Callable<ClusterLock>() {
			@Override
			public ClusterLock call() throws Exception {
				return new LocalLock(name, LocalInstance.this);
			}
		});
	}

	@Override
	public ClusterAtomicLong atomicLong(final String name) {
		return get(name, atomicLongs, new Callable<ClusterAtomicLong>() {
			@Override
			public ClusterAtomicLong call() throws Exception {
				return new LocalAtomicLong(name, LocalInstance.this);
			}
		});
	}

	void destroy(String name, Map<String, ?> map) {
		if (map.containsKey(name)) {
			synchronized(map) {
				if (map.containsKey(name)) {
					map.remove(name);
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private <T> T getWeak(String name, Map<String, WeakReference<T>> map, Callable<T> generator) {
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
	
	private <T> T get(String name, Map<String, T> map, Callable<T> generator) {
		T instance = map.containsKey(name) ? map.get(name) : null;
		if (instance == null) {
			synchronized(map) {
				instance = map.containsKey(name) ? map.get(name) : null;
				if (instance == null) {
					try {
						instance = generator.call();
						map.put(name, instance);
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
	public ClusterCountDownLatch countDownLatch(final String name) {
		return get(name, countDownLatches, new Callable<ClusterCountDownLatch>() {
			@Override
			public ClusterCountDownLatch call() throws Exception {
				return new LocalCountDownLatch(name, LocalInstance.this);
			}
		});
	}

	@Override
	public ClusterIdGenerator idGenerator(final String name) {
		return get(name, idGenerators, new Callable<ClusterIdGenerator>() {
			@Override
			public ClusterIdGenerator call() throws Exception {
				return new LocalIdGenerator(name, LocalInstance.this);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> ClusterMap<K, V> map(final String name) {
		return (ClusterMap<K, V>) get(name, maps, new Callable<ClusterMap<?, ?>>() {
			@SuppressWarnings("rawtypes")
			@Override
			public ClusterMap<?, ?> call() throws Exception {
				return new LocalMap(name, LocalInstance.this);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ClusterBlockingQueue<T> queue(final String name) {
		return (ClusterBlockingQueue<T>) get(name, queues, new Callable<ClusterBlockingQueue<?>>() {
			@SuppressWarnings("rawtypes")
			@Override
			public ClusterBlockingQueue<?> call() throws Exception {
				return new LocalBlockingQueue(name, LocalInstance.this);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ClusterSet<T> set(final String name) {
		return (ClusterSet<T>) get(name, sets, new Callable<ClusterSet<?>>() {
			@SuppressWarnings("rawtypes")
			@Override
			public ClusterSet<?> call() throws Exception {
				return new LocalSet(name, LocalInstance.this);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ClusterList<T> list(final String name) {
		return (ClusterList<T>) get(name, lists, new Callable<ClusterList<?>>() {
			@SuppressWarnings("rawtypes")
			@Override
			public ClusterList<?> call() throws Exception {
				return new LocalList(name, LocalInstance.this);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ClusterTopic<T> topic(final String name) {
		return (ClusterTopic<T>) get(name, topics, new Callable<ClusterTopic<?>>() {
			@SuppressWarnings("rawtypes")
			@Override
			public ClusterTopic<?> call() throws Exception {
				return new LocalTopic(name, LocalInstance.this);
			}
		});
	}
}

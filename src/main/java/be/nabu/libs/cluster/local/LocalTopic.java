package be.nabu.libs.cluster.local;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.nabu.libs.cluster.api.ClusterMessageListener;
import be.nabu.libs.cluster.api.ClusterSubscription;
import be.nabu.libs.cluster.api.ClusterTopic;

public class LocalTopic<T> implements ClusterTopic<T> {

	private List<ClusterMessageListener<T>> listeners = new ArrayList<ClusterMessageListener<T>>();
	private String name;
	private LocalInstance localInstance;
	private ExecutorService pool;
	
	public LocalTopic(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
		this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}

	@Override
	public void publish(final T message) {
		List<ClusterMessageListener<T>> listeners;
		synchronized (this.listeners) {
			listeners = new ArrayList<ClusterMessageListener<T>>(this.listeners);
		}
		for (final ClusterMessageListener<T> listener : listeners) {
			pool.submit(new Runnable() {
				public void run() {
					listener.onMessage(message);
				}
			});
		}
	}

	@Override
	public ClusterSubscription subscribe(final ClusterMessageListener<T> listener) {
		synchronized (this.listeners) {
			this.listeners.add(listener);
		}
		return new ClusterSubscription() {
			@Override
			public void unsubscribe() {
				synchronized (LocalTopic.this.listeners) {
					LocalTopic.this.listeners.remove(listener);
				}
			}
		};
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.topics);		
	}

}

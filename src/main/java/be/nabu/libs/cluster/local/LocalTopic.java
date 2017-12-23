package be.nabu.libs.cluster.local;

import java.util.ArrayList;
import java.util.List;

import be.nabu.libs.cluster.api.ClusterMessageListener;
import be.nabu.libs.cluster.api.ClusterSubscription;
import be.nabu.libs.cluster.api.ClusterTopic;

public class LocalTopic<T> implements ClusterTopic<T> {

	private List<ClusterMessageListener<T>> listeners = new ArrayList<ClusterMessageListener<T>>();
	private String name;
	private LocalInstance localInstance;
	
	public LocalTopic(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public void publish(T message) {
		List<ClusterMessageListener<T>> listeners;
		synchronized (this.listeners) {
			listeners = new ArrayList<ClusterMessageListener<T>>(this.listeners);
		}
		RuntimeException exception = null;
		for (ClusterMessageListener<T> listener : listeners) {
			try {
				listener.onMessage(message);
			}
			catch (RuntimeException e) {
				if (exception == null) {
					exception = e;
				}
				else {
					exception.addSuppressed(e);
				}
			}
		}
		if (exception != null) {
			throw exception;
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

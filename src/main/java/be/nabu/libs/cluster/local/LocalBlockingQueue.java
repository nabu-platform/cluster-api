package be.nabu.libs.cluster.local;

import java.util.concurrent.LinkedBlockingQueue;

import be.nabu.libs.cluster.api.ClusterBlockingQueue;

public class LocalBlockingQueue<T> extends LinkedBlockingQueue<T> implements ClusterBlockingQueue<T> {

	private static final long serialVersionUID = 2399104417080527337L;
	private String name;
	private LocalInstance localInstance;

	public LocalBlockingQueue(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.queues);		
	}

}

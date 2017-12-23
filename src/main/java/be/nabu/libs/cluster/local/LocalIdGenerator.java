package be.nabu.libs.cluster.local;

import java.util.concurrent.atomic.AtomicLong;

import be.nabu.libs.cluster.api.ClusterIdGenerator;

public class LocalIdGenerator implements ClusterIdGenerator {
	
	private AtomicLong id = new AtomicLong(1);
	private String name;
	private LocalInstance localInstance;
	
	public LocalIdGenerator(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public long newId() {
		return id.getAndIncrement();
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.idGenerators);
	}

}

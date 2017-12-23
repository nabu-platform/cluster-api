package be.nabu.libs.cluster.local;

import java.util.HashSet;

import be.nabu.libs.cluster.api.ClusterSet;

public class LocalSet<T> extends HashSet<T> implements ClusterSet<T> {

	private static final long serialVersionUID = 5589202123036812882L;
	private String name;
	private LocalInstance localInstance;

	public LocalSet(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.sets);
	}

}

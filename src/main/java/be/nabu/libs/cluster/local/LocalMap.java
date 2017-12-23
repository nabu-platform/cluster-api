package be.nabu.libs.cluster.local;

import java.util.HashMap;

import be.nabu.libs.cluster.api.ClusterMap;

public class LocalMap<K, V> extends HashMap<K, V> implements ClusterMap<K, V> {

	private static final long serialVersionUID = 8106842947760302717L;
	private String name;
	private LocalInstance localInstance;

	public LocalMap(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.maps);
	}

}

package be.nabu.libs.cluster.local;

import java.util.ArrayList;

import be.nabu.libs.cluster.api.ClusterList;

public class LocalList<T> extends ArrayList<T> implements ClusterList<T> {

	private static final long serialVersionUID = -4161515215757899149L;
	private String name;
	private LocalInstance localInstance;

	public LocalList(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.lists);		
	}

}

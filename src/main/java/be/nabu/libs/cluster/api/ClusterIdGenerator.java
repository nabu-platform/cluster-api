package be.nabu.libs.cluster.api;

public interface ClusterIdGenerator extends Destroyable {
	public long newId();
}

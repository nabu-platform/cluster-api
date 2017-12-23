package be.nabu.libs.cluster.api;

public interface ClusterMessageListener<T> {
	public void onMessage(T message);
}

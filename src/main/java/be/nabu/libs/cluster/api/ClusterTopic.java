package be.nabu.libs.cluster.api;

public interface ClusterTopic<T> extends Destroyable {
	public void publish(T message);
	public ClusterSubscription subscribe(ClusterMessageListener<T> listener);
}

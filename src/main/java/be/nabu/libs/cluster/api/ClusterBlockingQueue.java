package be.nabu.libs.cluster.api;

import java.util.concurrent.BlockingQueue;

public interface ClusterBlockingQueue<T> extends BlockingQueue<T>, Destroyable {
}

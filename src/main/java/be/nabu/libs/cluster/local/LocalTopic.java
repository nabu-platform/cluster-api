/*
* Copyright (C) 2017 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package be.nabu.libs.cluster.local;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.nabu.libs.cluster.api.ClusterMessageListener;
import be.nabu.libs.cluster.api.ClusterSubscription;
import be.nabu.libs.cluster.api.ClusterTopic;

public class LocalTopic<T> implements ClusterTopic<T> {

	private List<ClusterMessageListener<T>> listeners = new ArrayList<ClusterMessageListener<T>>();
	private String name;
	private LocalInstance localInstance;
	private ExecutorService pool;
	
	public LocalTopic(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
		this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}

	@Override
	public void publish(final T message) {
		List<ClusterMessageListener<T>> listeners;
		synchronized (this.listeners) {
			listeners = new ArrayList<ClusterMessageListener<T>>(this.listeners);
		}
		for (final ClusterMessageListener<T> listener : listeners) {
			pool.submit(new Runnable() {
				public void run() {
					listener.onMessage(message);
				}
			});
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

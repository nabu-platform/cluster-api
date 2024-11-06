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

package be.nabu.libs.cluster.api;

import java.util.List;

public interface ClusterInstance {
	public ClusterLock lock(String name);
	public ClusterAtomicLong atomicLong(String name);
	public ClusterCountDownLatch countDownLatch(String name);
	public ClusterIdGenerator idGenerator(String name);
	public <K, V> ClusterMap<K, V> map(String name);
	public <T> ClusterSet<T> set(String name);
	public <T> ClusterList<T> list(String name);
	public <T> ClusterBlockingQueue<T> queue(String name);
	public <T> ClusterTopic<T> topic(String name);
	public List<ClusterMember> members();
	public ClusterMembershipSubscription addMembershipListener(ClusterMembershipListener listener);
}

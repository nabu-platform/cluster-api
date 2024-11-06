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

import java.util.concurrent.atomic.AtomicLong;

import be.nabu.libs.cluster.api.ClusterAtomicLong;

public class LocalAtomicLong implements ClusterAtomicLong {

	private AtomicLong atomic = new AtomicLong();
	private String name;
	private LocalInstance localInstance;
	
	public LocalAtomicLong(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public long addAndGet(long delta) {
		return atomic.addAndGet(delta);
	}

	@Override
	public boolean compareAndSet(long expect, long update) {
		return atomic.compareAndSet(expect, update);
	}

	@Override
	public long decrementAndGet() {
		return atomic.decrementAndGet();
	}

	@Override
	public long get() {
		return atomic.get();
	}

	@Override
	public long getAndAdd(long delta) {
		return atomic.getAndAdd(delta);
	}

	@Override
	public long getAndDecrement() {
		return atomic.getAndDecrement();
	}

	@Override
	public long getAndIncrement() {
		return atomic.getAndIncrement();
	}

	@Override
	public long getAndSet(long newValue) {
		return atomic.getAndSet(newValue);
	}

	@Override
	public long incrementAndGet() {
		return atomic.incrementAndGet();
	}

	@Override
	public void lazySet(long newValue) {
		atomic.lazySet(newValue);
	}

	@Override
	public void set(long newValue) {
		atomic.set(newValue);
	}
	
	@Override
	public String toString() {
		return atomic.toString();
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.atomicLongs);
	}

}

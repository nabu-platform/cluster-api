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

public interface ClusterAtomicLong extends Destroyable {
	public long addAndGet(long delta);
	public boolean compareAndSet(long expect, long update);
	public long decrementAndGet();
	public long get();
	public long getAndAdd(long delta);
	public long getAndDecrement();
	public long getAndIncrement();
	public long getAndSet(long newValue);
	public long incrementAndGet();
	public void lazySet(long newValue);
	public void set(long newValue);
}

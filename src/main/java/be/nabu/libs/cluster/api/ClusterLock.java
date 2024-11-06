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

import java.util.concurrent.TimeUnit;

public interface ClusterLock extends Destroyable {
	// lock indefinitely until explicitly unlocked
	// note that this may wait indefinitely as well if someone else has the lock
	public void lock();
	// lock for max this time or until unlocked earlier
//	public void lock(long leaseTime, TimeUnit leaseTimeUnit);
	// release the lock
	public void unlock();
	// try to get a lock non-blocking
	public boolean tryLock();
	// try to get a lock and block for a given time
	public boolean tryLock(long timeout, TimeUnit timeoutUnit) throws InterruptedException;
	// with fixed lease time
//	public boolean tryLock(long timeout, TimeUnit timeoutUnit, long leaseTime, TimeUnit leaseTimeUnit) throws InterruptedException;
	// whether or not the lock is currently locked
	public boolean isLocked();
	// whether or not the lock is currently locked by the current thread
	public boolean isLockedByCurrentThread();
}

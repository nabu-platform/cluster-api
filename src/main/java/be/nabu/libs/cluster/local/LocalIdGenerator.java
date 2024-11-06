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

import be.nabu.libs.cluster.api.ClusterIdGenerator;

public class LocalIdGenerator implements ClusterIdGenerator {
	
	private AtomicLong id = new AtomicLong(1);
	private String name;
	private LocalInstance localInstance;
	
	public LocalIdGenerator(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public long newId() {
		return id.getAndIncrement();
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.idGenerators);
	}

}

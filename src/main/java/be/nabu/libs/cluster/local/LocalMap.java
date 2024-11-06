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

import java.util.HashMap;

import be.nabu.libs.cluster.api.ClusterMap;

public class LocalMap<K, V> extends HashMap<K, V> implements ClusterMap<K, V> {

	private static final long serialVersionUID = 8106842947760302717L;
	private String name;
	private LocalInstance localInstance;

	public LocalMap(String name, LocalInstance localInstance) {
		this.name = name;
		this.localInstance = localInstance;
	}

	@Override
	public void destroy() {
		localInstance.destroy(name, localInstance.maps);
	}

}

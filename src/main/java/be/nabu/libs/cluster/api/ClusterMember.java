package be.nabu.libs.cluster.api;

import java.net.InetAddress;

public interface ClusterMember {
	public String getName();
	public String getGroup();
	public InetAddress getAddress();
}

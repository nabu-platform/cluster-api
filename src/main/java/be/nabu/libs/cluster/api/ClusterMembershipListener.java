package be.nabu.libs.cluster.api;

public interface ClusterMembershipListener {
	public void memberAdded(ClusterMember member);
	public void memberRemoved(ClusterMember member);
}

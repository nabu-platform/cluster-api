package be.nabu.libs.cluster.execution.api;

import be.nabu.libs.cluster.execution.api.ClusterTask.State;

public interface ClusterExecutionLog {
	// the id of the one who created the log
	public String getExecutorId();
	// the state of the log
	public State getState();
	// the message
	public String getMessage();
	// the message code
	public String getCode();
}

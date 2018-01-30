package be.nabu.libs.cluster.execution.api;

import java.net.URI;
import java.util.Date;
import java.util.List;

public interface ClusterTask {
	
	// a unique incremental id for this task
	public Long getId();
	// the (group of) executors that can pick up this task
	// $any means anyone can
	public String getTargetExecutor();
	// when the task was submitted
	public Date getSubmitted();
	// the service that should be triggered by the task
	public String getServiceId();
	// the URI of the input for the service
	public URI getInput();
	// the state
	public State getState();
	// the logs attached to this execution
	public List<ClusterExecutionLog> getLogs();
	// the id of the executor, it is set when the task is picked up for execution
	// this is to easily determine which tasks should be rerun on server failure
	public String getExecutorId();
	
	public enum State {
		// the entry was created
		CREATED,
		// the entry is running
		RUNNING,
		// the execution ended with an error, it is currently not handled
		ERROR,
		// the execution ended with an error and it was decided that it should stay that way
		FAILED,
		// the execution was successfully completed
		DONE,
		// the execution was cancelled before it even started
		CANCELLED
	}
}

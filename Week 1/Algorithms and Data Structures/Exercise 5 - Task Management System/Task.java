package tasks;

public class Task 
{
	private String taskId,taskName,status;
	public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return String.format("%-10s %-30s %-15s%n", taskId, taskName, status);
    }    
}

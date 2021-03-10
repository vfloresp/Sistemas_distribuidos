package interfaces;

import java.io.Serializable;

public class Task implements Serializable{
    private String taskId;
    private String requirementId;
    private Long length;
    private String output;

    public Task(String aTaskId, String aRequirementId, long aLength){
        taskId=aTaskId;
        requirementId=aRequirementId;
        length= aLength;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

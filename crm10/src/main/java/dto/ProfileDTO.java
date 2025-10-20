package dto;

import java.sql.Timestamp;

public class ProfileDTO {
	private int id;
	private String taskName;
	private String projectName;
	private Timestamp startDate;
	private Timestamp endDate;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ProfileDTO() {
		super();
	}
	public ProfileDTO(int id, String taskName, String projectName, Timestamp startDate, Timestamp endDate,
			String status) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	
	
}

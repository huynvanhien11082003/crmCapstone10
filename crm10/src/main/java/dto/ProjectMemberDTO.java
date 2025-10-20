package dto;

import java.time.LocalDateTime;

public class ProjectMemberDTO {
	private int  percent;
	private String status;
	private String firstName;
	private String lastName;
	private String taskName;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public ProjectMemberDTO() {
		super();
	}
	public ProjectMemberDTO(int percent, String status, String firstName, String lastName, String taskName,
			LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.percent = percent;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	
}

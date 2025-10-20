package entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "taskprogress")
public class TaskProgress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "progresspercent")
	private int progressPercent;
	@Column(name ="startdate")
	private LocalDateTime startDate;
	@Column(name = "enddate")
	private LocalDateTime endDate;
	@Column(name = "status")
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne
	@JoinColumn(name="usersid")
	private Users usersid;
	@ManyToOne
	@JoinColumn(name="taskid")
	private Tasks taskid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProgressPercent() {
		return progressPercent;
	}
	public void setProgressPercent(int progressPercent) {
		this.progressPercent = progressPercent;
	}
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
	public Users getUsersid() {
		return usersid;
	}
	public void setUsersid(Users usersid) {
		this.usersid = usersid;
	}
	public Tasks getTaskid() {
		return taskid;
	}
	public void setTaskid(Tasks taskid) {
		this.taskid = taskid;
	}
	public TaskProgress() {
		super();
	}
	public TaskProgress(int id, int progressPercent, LocalDateTime startDate, LocalDateTime endDate, String status,
			Users usersid, Tasks taskid) {
		super();
		this.id = id;
		this.progressPercent = progressPercent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.usersid = usersid;
		this.taskid = taskid;
	}
	
	
	
}

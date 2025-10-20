package entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nametasks")
    private String nameTasks;
    @Column(name = "startdate")
    private LocalDateTime startDate;
    @Column(name = "enddate")
    private LocalDateTime endDate;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "projectsid")
    private Projects projectsid;
    
    @OneToMany(mappedBy = "taskid")
    private List<TaskProgress> taskprogress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameTasks() {
		return nameTasks;
	}

	public void setNameTasks(String nameTasks) {
		this.nameTasks = nameTasks;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Projects getProjectsid() {
		return projectsid;
	}

	public void setProjectsid(Projects projectsid) {
		this.projectsid = projectsid;
	}

	public List<TaskProgress> getTaskprogress() {
		return taskprogress;
	}

	public void setTaskprogress(List<TaskProgress> taskprogress) {
		this.taskprogress = taskprogress;
	}

	public Tasks() {
		super();
	}

	public Tasks(int id, String nameTasks, LocalDateTime startDate, LocalDateTime endDate, String status,
			Projects projectsid, List<TaskProgress> taskprogress) {
		super();
		this.id = id;
		this.nameTasks = nameTasks;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectsid = projectsid;
		this.taskprogress = taskprogress;
	}
    
    
    

}
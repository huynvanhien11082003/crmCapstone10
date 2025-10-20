package entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "projects")
public class Projects {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "startdate")
    private LocalDateTime startdate;
    @Column(name = "enddate")
    private LocalDateTime enddate;

    @OneToMany(mappedBy = "projectsid")
    private List<ProjectsMember> createdByUserId;
    
    @OneToMany(mappedBy = "projectsid")
    private List<Tasks> taskList;

	public List<Tasks> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Tasks> taskList) {
		this.taskList = taskList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDateTime startdate) {
		this.startdate = startdate;
	}

	public LocalDateTime getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}

	
	public List<ProjectsMember> getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(List<ProjectsMember> createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Projects() {
		super();
	}

	public Projects(int id, String name, LocalDateTime startdate, LocalDateTime enddate,
			List<ProjectsMember> createdByUserId, List<Tasks> taskList) {
		super();
		this.id = id;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.createdByUserId = createdByUserId;
		this.taskList = taskList;
	}

	

	
	
    
    

}
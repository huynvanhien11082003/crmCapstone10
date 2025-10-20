package entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "projectsmember")
public class ProjectsMember {
    @Id
    private int id;
    @Column(name = "joindate")
    private LocalDateTime joindate;
    
    
    @ManyToOne
    @JoinColumn(name = "userid")
    private Users usersid;

    @ManyToOne
    @JoinColumn(name = "projectsid")
    private Projects projectsid;
    
    private String status;
    private int  percent;
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getJoindate() {
		return joindate;
	}

	public void setJoindate(LocalDateTime joindate) {
		this.joindate = joindate;
	}

	public Users getUsersid() {
		return usersid;
	}

	public void setUsersid(Users usersid) {
		this.usersid = usersid;
	}

	public Projects getProjectsid() {
		return projectsid;
	}

	public void setProjectsid(Projects projectsid) {
		this.projectsid = projectsid;
	}

	public ProjectsMember() {
		super();
	}

	public ProjectsMember(int id, LocalDateTime joindate, Users usersid, Projects projectsid, String status,
			int percent) {
		super();
		this.id = id;
		this.joindate = joindate;
		this.usersid = usersid;
		this.projectsid = projectsid;
		this.status = status;
		this.percent = percent;
	}

	

	
	
    
}
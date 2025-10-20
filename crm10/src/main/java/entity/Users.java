package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleid")
    private Roles rolesid;
    
    @OneToMany(mappedBy = "usersid")
    private List<TaskProgress> taskprogress;
    
    @OneToMany(mappedBy = "usersid")
    private List<ProjectsMember> projectsMember;
    
    
	public List<TaskProgress> getTaskprogress() {
		return taskprogress;
	}

	public void setTaskprogress(List<TaskProgress> taskprogress) {
		this.taskprogress = taskprogress;
	}

	public List<ProjectsMember> getProjectsMember() {
		return projectsMember;
	}

	public void setProjectsMember(List<ProjectsMember> projectsMember) {
		this.projectsMember = projectsMember;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRolesid() {
		return rolesid;
	}

	public void setRolesid(Roles rolesid) {
		this.rolesid = rolesid;
	}


	public Users() {
		super();
	}

	public Users(int id, String firstname, String lastname, String username, String password, Roles rolesid,
			List<TaskProgress> taskprogress, List<ProjectsMember> projectsMember) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.rolesid = rolesid;
		this.taskprogress = taskprogress;
		this.projectsMember = projectsMember;
	}

	
    

}
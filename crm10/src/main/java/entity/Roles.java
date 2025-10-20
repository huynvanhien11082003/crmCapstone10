package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "rolesid")
    private List<Users> usersid;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Users> getUsersid() {
		return usersid;
	}

	public void setUsersid(List<Users> usersid) {
		this.usersid = usersid;
	}
	

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roles(int id, String name, String description, List<Users> usersid) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.usersid = usersid;
	}
    
    
}

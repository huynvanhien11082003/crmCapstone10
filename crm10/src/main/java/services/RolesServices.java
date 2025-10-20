package services;

import java.util.List;

import entity.Roles;
import repository.RoleRepository;

public class RolesServices {

	private RoleRepository repository = new RoleRepository();
	
	public List<Roles> getAllRoles()
	{
		return repository.findALl();
	}
	public boolean addRoles(Roles roles)
	{
		return repository.save(roles);
	}
	public Roles findRolesById(int id)
	{
		return repository.findRoleById(id);
	}
	public boolean updateRoles(Roles roles)
	{
		return repository.updateRoles(roles);
	}
	public boolean deleteRoles(int id)
	{
		return repository.deleteRoles(id);
	}
}

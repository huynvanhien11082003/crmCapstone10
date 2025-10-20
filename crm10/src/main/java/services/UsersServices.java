package services;

import java.util.List;

import dto.UsersDetail;
import entity.Users;
import repository.UsersRepository;

public class UsersServices {
	private UsersRepository repository = new UsersRepository();
	public List<Users> getAllUsers()
	{
		return repository.findAll();
	}
	public boolean addUsers(Users users)
	{
		return repository.save(users);
	}
	public boolean updateUsers(Users users)
	{
		return repository.update(users);
	}
	public Users findById(int id)
	{
		return repository.findUsersById(id);
	}
	public boolean deleteUsers(int id)
	{
		return repository.deleteUsers(id);
	}
	public List<UsersDetail> getUsersDetail(int id)
	{
		return repository.usersDetail(id);
	}
	public Users login(String email, String password)
	{
		return repository.findByEmailAndPassword(email, password);
	}
}

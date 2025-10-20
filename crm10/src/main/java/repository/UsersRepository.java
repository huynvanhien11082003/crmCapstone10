package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import dto.UsersDetail;
import entity.Roles;
import entity.Users;

public class UsersRepository {
	public List<Users> findAll()
	{
		List<Users> usersList = new ArrayList<Users>();
		String query = "SELECT *\r\n"
				+ "FROM users u ";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Users users = new Users();
				users.setId(rs.getInt("id"));
				users.setFirstname(rs.getString("firstname"));
				users.setLastname(rs.getString("lastname"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				Roles roles = new Roles();
				roles.setId(rs.getInt("roleid"));;
				users.setRolesid(roles);
				
				usersList.add(users);
				
			}
		} catch (Exception e) {
			System.out.println("Lỗi câu findAll" + e.getMessage());
		}
		return usersList;
	}
	
	public boolean save(Users users)
	{
		boolean result = false;
		String query = "INSERT INTO users (firstname, lastname, username, password, roleid) VALUES (?,?,?,?,?) ";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, users.getFirstname());
			ps.setString(2, users.getLastname());
			ps.setString(3, users.getUsername());
			ps.setString(4, users.getPassword());
			ps.setInt(5, users.getRolesid().getId());
			
			int rows = ps.executeUpdate();
			if(rows > 0)
			{
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Lỗi Khi Thêm Users" + e.getMessage());
		}
		return result;
	}
	
	
	
	public Users findUsersById(int id)
	{
		Users users =  null;
		
		String query =  "SELECT *\r\n"
				+ "FROM users u \r\n"
				+ "WHERE u.id = ?";
		Connection con = MySQLConfig.getConnection();
		try {
			
			PreparedStatement preparedStatement  = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				users = new Users();
				users.setId(rs.getInt("id"));
				users.setFirstname(rs.getString("firstname"));
				users.setLastname(rs.getString("lastname"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				Roles roles = new Roles();
				roles.setId(rs.getInt("roleid"));;
				users.setRolesid(roles);
			}
		} catch (Exception e) {
			System.out.println("Lỗi findById: " + e.getMessage());
		}
		return users;
	}
	
	
	public boolean update(Users users)
	{
		boolean result = false;
		String query ="UPDATE users\r\n"
				+ "SET firstname = ?, lastname =?, username = ?,  password = ?, roleid =?\r\n"
				+ "where  id = ?";
		Connection con = MySQLConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, users.getFirstname());
			ps.setString(2, users.getLastname());
			ps.setString(3, users.getUsername());
			ps.setString(4, users.getPassword());
			ps.setInt(5, users.getRolesid().getId());
			ps.setInt(6, users.getId());
			int rows = ps.executeUpdate();
			if(rows >0)
			{
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Lỗi câu truy vấn update "  +e.getMessage());
		}
		return result;
	}
	
	public boolean deleteUsers(int id)
	{
		boolean result = false;
		
		String query = "DELETE FROM users \r\n"
				+ "WHERE id = ?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			int rows = ps.executeUpdate();
			if(rows >0)
			{
				result = true;
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi câu truy vấn delete" + e.getMessage());
		}
		return result;
	}
	
	
	public List<UsersDetail> usersDetail(int id)
	{
		String query ="SELECT u.firstname, u.lastname ,  t.status, t.progresspercent, t2.nametasks , t2.startdate ,t2.enddate\r\n"
				+ "FROM taskprogress t JOIN users u on t.usersid = u.id\r\n"
				+ "					JOIN tasks t2 on t.taskid = t2.id \r\n"
				+ "WHERE t.usersid = ?";
		List<UsersDetail> usersDeTailList = new ArrayList<UsersDetail>();
		Connection con =  MySQLConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next())
			{
				UsersDetail usersDetail = new UsersDetail();
				usersDetail.setFirstName(rs.getString("firstname"));
				usersDetail.setLastName(rs.getString("lastname"));
				usersDetail.setStatus(rs.getString("status"));
				usersDetail.setProgressPercent(rs.getInt("progresspercent"));
				usersDetail.setTaskName(rs.getString("nametasks"));
				usersDetail.setStartDate(rs.getTimestamp("startdate").toLocalDateTime());
				usersDetail.setEndDate(rs.getTimestamp("enddate").toLocalDateTime());
				
				usersDeTailList.add(usersDetail);
			}
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn usersDetail" + e.getMessage());
		}
		
		return usersDeTailList;
	}
	
	public Users findByEmailAndPassword(String username, String password)
	{
		Users users  = null;
		String query = "SELECT u.id,u.username, r.id AS roleid , r.name AS rolename \r\n"
				+ "FROM users u JOIN roles r on u.roleid = r.id\r\n"
				+ "WHERE u.username = ? AND u.password =?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs  = ps.executeQuery();
			
			if(rs.next())
			{
				users = new Users();
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("username"));
				Roles roles = new Roles();
				roles.setId(rs.getInt("roleid"));
				roles.setName(rs.getString("rolename"));
				users.setRolesid(roles);
				
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn  find ByEmailAndPassword" + e.getMessage());
		}
		return users;
	}

}

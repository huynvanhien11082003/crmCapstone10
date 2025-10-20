package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Roles;

public class RoleRepository {
	public List<Roles> findALl()
	{
		List<Roles> rolesList = new ArrayList<Roles>();
		String query = "SELECT * FROM roles r";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Roles roles = new Roles();
				roles.setId(rs.getInt("id"));
				roles.setName(rs.getString("name"));
				roles.setDescription(rs.getString("description"));
				
				rolesList.add(roles);
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi câu truy vấn findAll Roles");
		}
		
		return rolesList;
	}
	public boolean save(Roles roles)
	{
		String query = "INSERT INTO roles (name,description) VALUES\r\n"
				+ "(?,?)";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, roles.getName());
			ps.setString(2, roles.getDescription());
			
			int rows = ps.executeUpdate();
			if(rows > 0)
			{
				return true;
			}
		} catch (Exception e) {
			System.out.println("Lỗi insert roles" + e.getMessage());
		}
		return false;
	}
	public Roles findRoleById(int id)
	{
		Roles roles = new Roles();
		String query = "SELECT * FROM roles r WHERE r.id = ?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				roles.setId(rs.getInt("id"));
				roles.setName(rs.getString("name"));
				roles.setDescription(rs.getString("description"));
				
				
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi findRoleById");
		}
		return roles;
	}
	public boolean updateRoles(Roles roles)
	{
		boolean result = false;
		
		String query = "UPDATE roles r \r\n"
				+ "SET name = ?, description  = ?\r\n"
				+ "WHERE r.id  = ?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, roles.getName());
			ps.setString(2,roles.getDescription());
			ps.setInt(3, roles.getId());
			
			int rows = ps.executeUpdate();
			if(rows > 0	)
			{
				result = true;
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi query update");
		}
		
		return result;
	}
	public boolean deleteRoles(int id)
	{
		boolean result = false;
		String query = "DELETE FROM roles WHERE id = ?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			int rows = ps.executeUpdate();
			if(rows > 0 )
			{
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Lỗi câu truy vấn delete" + e.getMessage());
		}
		
		return result;
	}
}

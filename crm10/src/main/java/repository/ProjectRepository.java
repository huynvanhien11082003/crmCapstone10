package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Projects;


public class ProjectRepository {
	public List<Projects> findAll()
	{
		List<Projects> projectList = new ArrayList<Projects>();
		
		String query = "SELECT * FROM projects p ";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next())
			{
				Projects projects = new Projects();
				projects.setId(resultSet.getInt("id"));
				projects.setName(resultSet.getString("name"));
				projects.setStartdate(resultSet.getTimestamp("startdate").toLocalDateTime());
				projects.setEnddate(resultSet.getTimestamp("enddate").toLocalDateTime());
				
				projectList.add(projects);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn câu findAll projects" + e.getMessage());
		}
		return projectList;
	}
	
	public boolean addProject(Projects projects)
	{
		boolean result = false;
		
		String query = "INSERT INTO projects (name, startdate, enddate) VALUES\r\n"
				+ "(?, ?, ?)";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, projects.getName());
			ps.setTimestamp(2, Timestamp.valueOf(projects.getStartdate()));
			ps.setTimestamp(3, Timestamp.valueOf(projects.getEnddate()));
			
			int rows = ps.executeUpdate();
			if(rows > 0)
			{
				result = true;
			}
			
			
		} catch (Exception e) {
			System.out.println("Lỗi câu truy vấn insert");
		}
		
		return result;
		
	}
	
	public Projects findById(int id)
	{
		Projects projects = new Projects();
		String query = "SELECT  * from projects p WHERE p.id = ?";
		
		Connection con  = MySQLConfig.getConnection();
		
		try {
			PreparedStatement  ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				projects.setId(rs.getInt("id"));
				projects.setName(rs.getString("name"));
				projects.setStartdate(rs.getTimestamp("startdate").toLocalDateTime());
				projects.setEnddate(rs.getTimestamp("enddate").toLocalDateTime());
				
				
			}
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn findById Projects"+ e.getMessage());
		}
		return projects;
	}
	
	public boolean updateProject(Projects projects)
	{
		boolean result = false;
		
		String query = "UPDATE projects p\r\n"
				+ "SET name = ?, startdate = ?, enddate = ?\r\n"
				+ "WHERE p.id = ?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, projects.getName());
			ps.setTimestamp(2, Timestamp.valueOf(projects.getStartdate()));
			ps.setTimestamp(3, Timestamp.valueOf(projects.getEnddate()));
			ps.setInt(4, projects.getId());
			
			
			int rows = ps.executeUpdate();
			if(rows > 0)
			{
				result = true;
			}
			
			
		} catch (Exception e) {
			System.out.println("Lỗi câu truy vấn update" + e.getMessage());
			
		}
		return result;
	}
	
	public boolean deleteProject(int id)
	{
		boolean result = false;
		
		String query = "DELETE FROM projects WHERE id = ?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps =con.prepareStatement(query);
			ps.setInt(1, id);
			
			int rows = ps.executeUpdate();
			
			if(rows > 0)
			{
				result = true;
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi câu truy vấn delete");
			
		}
		
		return result;
	}
	
	
}

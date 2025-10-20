package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import dto.ProjectMemberDTO;
import entity.Projects;
import entity.ProjectsMember;
import entity.Users;

public class ProjectMemberRepository {
	public List<ProjectsMember> findAll()
	{
		List<ProjectsMember> projectsMembersList  = new ArrayList<ProjectsMember>();
		
		String query =  "SELECT * FROM projectsmember p ";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				ProjectsMember projectsMember = new ProjectsMember();
				projectsMember.setId(rs.getInt("id"));
				projectsMember.setJoindate(rs.getTimestamp("joindate").toLocalDateTime());
				
				Users users = new Users();
				users.setId(rs.getInt("userid"));
				projectsMember.setUsersid(users);
				
				
				Projects projects = new Projects();
				projects.setId(rs.getInt("projectsid"));
				projectsMember.setProjectsid(projects);
				
				projectsMember.setStatus(rs.getString("status"));
				projectsMember.setPercent(rs.getInt("percent"));
				
				projectsMembersList.add(projectsMember);
				
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi findAll projectMember"+e.getMessage());
		}
		return projectsMembersList;
	}
	
	public List<ProjectMemberDTO> getDetailsByProjectId(int id)
	{
		List<ProjectMemberDTO> projectMemberDTOList =new ArrayList<ProjectMemberDTO>();
		
		String query = "SELECT t.status ,p2.percent, u.firstname ,u.lastname ,t.nametasks \r\n"
				+ "FROM tasks t JOIN projects p on t.projectsid = p.id \r\n"
				+ "			JOIN projectsmember p2 on p.id  = p2.projectsid  \r\n"
				+ "			JOIN users u on p2.userid = u.id\r\n"
				+ "WHERE p.id = ?  ";
		
		
		Connection con =  MySQLConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				String status = rs.getString("status");
	            int percent = rs.getInt("percent");
	            String firstname = rs.getString("firstname");
	            String lastname = rs.getString("lastname");
	            String taskName = rs.getString("nametasks");
	            
	            ProjectMemberDTO dto = new ProjectMemberDTO();
	            dto.setFirstName(firstname);
	            dto.setLastName(lastname);
	            dto.setPercent(percent);
	            dto.setStatus(status);
	            dto.setTaskName(taskName);
	            
	            
	            projectMemberDTOList.add(dto);
	            
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn getDetail" + e.getMessage());
		}
		
		
		return  projectMemberDTOList;
	}
}

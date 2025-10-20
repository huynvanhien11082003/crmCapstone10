package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import dto.ProfileDTO;
import entity.Projects;
import entity.Roles;
import entity.TaskProgress;
import entity.Tasks;
import entity.Users;

public class ProfileRepository {
	public Users findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		try (Connection conn = MySQLConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Users u = new Users();
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));

				Roles roles = new Roles();
				roles.setId(rs.getInt("roleid"));

				u.setRolesid(roles);
				return u;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TaskProgress findTaskByUserId(int id) {
		TaskProgress taskProgress = null;
		String query = "SELECT *\r\n" + "FROM  taskprogress t \r\n" + "WHERE t.usersid =?";
		Connection con = MySQLConfig.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				taskProgress = new TaskProgress();
				taskProgress.setId(rs.getInt("id"));
				taskProgress.setProgressPercent(rs.getInt("progresspercent"));
				taskProgress.setStatus(rs.getString("status"));
				Users users = new Users();
				users.setId(rs.getInt("usersid"));
				
				taskProgress.setUsersid(users);
				
				Tasks tasks = new Tasks();
				tasks.setId(rs.getInt("taskid"));
				taskProgress.setTaskid(tasks);
				
				taskProgress.setStartDate(Timestamp.valueOf(rs.getString("startdate")).toLocalDateTime());
				taskProgress.setStartDate(Timestamp.valueOf(rs.getString("enddate")).toLocalDateTime());
				
				return taskProgress;
			}

		} catch (Exception e) {
			System.out.println("findTaskByUserid" + e.getMessage());
		}
		return  null;
	}
	public List<ProfileDTO> findTaskProjectByUserId(int  id)
	{
		List<ProfileDTO> list =  new ArrayList<ProfileDTO>();
		
		String query = "SELECT t.id, t.nametasks, p.name,t.startdate , t.enddate,t.status \r\n"
				+ "FROM tasks t JOIN projects p on t.projectsid  = p.id\r\n"
				+ "			JOIN projectsmember p2 on p.id =p2.projectsid \r\n"
				+ "WHERE p2.userid = ?";
		
		Connection con = MySQLConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				ProfileDTO profileDTO = new ProfileDTO();
				profileDTO.setId(rs.getInt("id"));
				profileDTO.setTaskName(rs.getString("nametasks"));
				profileDTO.setProjectName(rs.getString("name"));
				profileDTO.setStartDate(rs.getTimestamp("startdate"));
				profileDTO.setEndDate(rs.getTimestamp("enddate"));
				profileDTO.setStatus(rs.getString("status"));
				
				list.add(profileDTO);
			}
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn findTaskProjectByUserId");
		}
		return list;
	}
	
}

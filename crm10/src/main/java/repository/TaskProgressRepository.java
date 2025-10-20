package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Projects;
import entity.TaskProgress;
import entity.Tasks;
import entity.Users;

public class TaskProgressRepository {
	public List<TaskProgress> findTaskByUserId(int userId)
	{
		List<TaskProgress> list = new ArrayList<TaskProgress>();
		String query = "SELECT u.firstname, u.lastname ,  t.status, t.progresspercent \r\n"
				+ "FROM taskprogress t JOIN users u on t.usersid = u.id\r\n"
				+ "WHERE t.usersid = ?";
		
		Connection con = MySQLConfig.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				TaskProgress taskProgress = new TaskProgress();
				taskProgress.setId(rs.getInt("id"));
				taskProgress.setStartDate(rs.getTimestamp("startdate").toLocalDateTime());
				taskProgress.setEndDate(rs.getTimestamp("enddate").toLocalDateTime());
				taskProgress.setProgressPercent(rs.getInt("processpercent"));
				taskProgress.setStatus(rs.getString("status"));
				
				list.add(taskProgress);
				
				
				
			}
			
		} catch (Exception e) {
			System.out.println("lỗi câu truy vấn tìm findTaskByUserId" +e.getMessage());
		}
		return list;
	}
	public List<TaskProgress> findAll()
	{
		List<TaskProgress> taskProgressList = new ArrayList<TaskProgress>();
		
		String query = "SELECT t.id , t2.nametasks, p.name , u.firstname , u.lastname  , t.startdate ,t.enddate,t.status \r\n"
				+ "FROM taskprogress t JOIN tasks t2 on t.taskid = t2.id \r\n"
				+ "					JOIN projects p on  t2.projectsid = p.id \r\n"
				+ "					JOIN users u on t.usersid = u.id ";
		
		Connection  con =  MySQLConfig.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				TaskProgress taskProgress = new TaskProgress();
				taskProgress.setId(rs.getInt("id"));
				taskProgress.setStartDate(rs.getTimestamp("startdate").toLocalDateTime());
				taskProgress.setEndDate(rs.getTimestamp("enddate").toLocalDateTime());
				taskProgress.setStatus(rs.getString("status"));
				
				
				Tasks tasks = new Tasks();
				tasks.setNameTasks(rs.getString("nametasks"));
				
				Projects projects = new Projects();
				projects.setName(rs.getString("name"));
				
				Users users = new Users();
				users.setFirstname(rs.getString("firstname"));
				users.setLastname(rs.getString("lastname"));
				
				tasks.setProjectsid(projects);
				taskProgress.setTaskid(tasks);
				taskProgress.setUsersid(users);
				
				taskProgressList.add(taskProgress);
				
				
				
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi truy vấn taskprogress: " + e.getMessage());
		}
		
		
		return  taskProgressList;
	}
	public int insertTaskProgress(int taskId, int userId, Timestamp startDate, Timestamp endDate) {
        int result = 0;
        String query = "INSERT INTO taskprogress (taskid, usersid, startdate, enddate, status) VALUES (?, ?, ?, ?, 'Chưa hoàn thành')";
        try (Connection con = MySQLConfig.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, taskId);
            ps.setInt(2, userId);
            ps.setTimestamp(3, startDate);
            ps.setTimestamp(4, endDate);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getProjectIdByName(String projectName) {
        int id = 0;
        String query = "SELECT id FROM projects WHERE name = ?";
        try (Connection con = MySQLConfig.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, projectName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getUserIdByFullname(String fullname) {
        int id = 0;
        String[] parts = fullname.trim().split(" ", 2);
        String lastname = parts[0];
        String firstname = parts.length > 1 ? parts[1] : "";
        String query = "SELECT id FROM users WHERE firstname = ? AND lastname = ?";
        try (Connection con = MySQLConfig.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getTaskIdByName(String taskName, int projectId) {
        int id = 0;
        String query = "SELECT id FROM tasks WHERE nametasks = ? AND projectsid = ?";
        try (Connection con = MySQLConfig.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, taskName);
            ps.setInt(2, projectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public int deleteTaskProgress(int id) {
        int result = 0;
        String query = "DELETE FROM taskprogress WHERE id = ?";
        try (Connection con = MySQLConfig.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public int updateTaskProgress(TaskProgress taskProgress) {
        int result = 0;
        String query = "UPDATE taskprogress SET taskid = ?, usersid = ?, startdate = ?, enddate = ?, status = ?, progresspercent = ? WHERE id = ?";

        try (Connection con = MySQLConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, taskProgress.getTaskid().getId());
            ps.setInt(2, taskProgress.getUsersid().getId());
            ps.setTimestamp(3, Timestamp.valueOf(taskProgress.getStartDate()));
            ps.setTimestamp(4, Timestamp.valueOf(taskProgress.getEndDate()));
            ps.setString(5, taskProgress.getStatus());
            ps.setInt(6, taskProgress.getProgressPercent());
            ps.setInt(7, taskProgress.getId());

            result = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi khi update taskprogress: " + e.getMessage());
        }

        return result;
    }
    public TaskProgress findById(int id) {
        TaskProgress tp = null;
        String query = "SELECT tp.*, t.nametasks, u.firstname, u.lastname, p.name AS projectname " +
                       "FROM taskprogress tp " +
                       "JOIN tasks t ON tp.taskid = t.id " +
                       "JOIN users u ON tp.usersid = u.id " +
                       "JOIN projects p ON t.projectsid = p.id " +
                       "WHERE tp.id = ?";

        try (Connection con = MySQLConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tp = new TaskProgress();
                tp.setId(rs.getInt("id"));
                tp.setStatus(rs.getString("status"));
                tp.setProgressPercent(rs.getInt("progresspercent"));
                tp.setStartDate(rs.getTimestamp("startdate").toLocalDateTime());
                tp.setEndDate(rs.getTimestamp("enddate").toLocalDateTime());

                
                Tasks task = new Tasks();
                task.setId(rs.getInt("taskid"));
                task.setNameTasks(rs.getString("nametasks"));

                
                Projects project = new Projects();
                project.setName(rs.getString("projectname"));
                task.setProjectsid(project);

               
                Users user = new Users();
                user.setId(rs.getInt("usersid"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));

               
                tp.setTaskid(task);
                tp.setUsersid(user);
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi tìm TaskProgress theo ID: " + e.getMessage());
        }

        return tp;
    }

}

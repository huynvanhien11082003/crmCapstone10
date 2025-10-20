package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Tasks;

public class TaskRepository {
	public List<Tasks> findAllTasks() {
	    List<Tasks> list = new ArrayList<Tasks>();
	    String query = "SELECT * FROM tasks";
	    try (Connection con = MySQLConfig.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Tasks t = new Tasks();
	            t.setId(rs.getInt("id"));
	            t.setNameTasks(rs.getString("nametasks"));
	            list.add(t);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}

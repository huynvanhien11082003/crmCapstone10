package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConfig {
	public static Connection getConnection()
	{
		Connection con = null;
		try {
			String url="jdbc:mysql://localhost:3307/crm10";
			String username = "root";
			String password = "admin123";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Loi ket noi co so du lieu " + e.getMessage());
		}
		return con;
	}
}

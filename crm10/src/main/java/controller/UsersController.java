package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UsersDetail;
import entity.Roles;
import entity.Users;
import services.UsersServices;


@WebServlet(name = "UsersController",urlPatterns = {"/user-table", "/user-add","/user-detail","/user-edit","/user-delete"})
public class UsersController extends HttpServlet {
	
	UsersServices services = new UsersServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		
		switch (path) {
		case "/user-table": {
			List<Users> listUsers = services.getAllUsers();
			
			req.setAttribute("listUsers", listUsers);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			break;
		}
		case "/user-detail": {
			int id = Integer.parseInt(req.getParameter("id"));
			Users users =  services.findById(id);
			List<UsersDetail> listUserDetai = services.getUsersDetail(id);
			req.setAttribute("listUserDetail", listUserDetai);
			req.setAttribute("users", users);
			req.getRequestDispatcher("user-detail.jsp").forward(req, resp);
			
			
			
			break;
		}
		case "/user-add": {
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		}
		case "/user-edit": {
			int id = Integer.parseInt(req.getParameter("id"));
			Users users = services.findById(id);
			req.setAttribute("users", users);
			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
			
			break;
		}
		case "/user-delete": {
			int id = Integer.parseInt(req.getParameter("id"));
			services.deleteUsers(id);
			resp.sendRedirect("user-table");
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String path = req.getServletPath();
		
		switch (path) {
		case "/user-add": {
			String firstName  = req.getParameter("firstName");
			String lastName  = req.getParameter("lastName");
			String userName  = req.getParameter("userName");
			String password  = req.getParameter("password");
			int roles  = Integer.parseInt(req.getParameter("roles"));
			
			Users users = new Users();
			users.setFirstname(firstName);
			users.setLastname(lastName);
			users.setUsername(userName);
			users.setPassword(password);
			
			Roles rolesAdd = new Roles();
			rolesAdd.setId(roles);
			users.setRolesid(rolesAdd);
			
			services.addUsers(users);
			
			resp.sendRedirect("user-table");
			
			break;
		}
		case "/user-edit":{
			String idStr = req.getParameter("id");
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			String userName = req.getParameter("username");
			String password = req.getParameter("password");
			int rolesId = Integer.parseInt(req.getParameter("roles"));
			
			int id =  Integer.parseInt(idStr);
			
			Users users = new Users();
			users.setId(id);
			users.setFirstname(firstName);
			users.setLastname(lastName);
			users.setUsername(userName);
			users.setUsername(userName);
			users.setPassword(password);
			
			Roles rolesNew = new Roles();
			rolesNew.setId(rolesId);
			users.setRolesid(rolesNew);
			
			boolean result = services.updateUsers(users);
			if(result)
			{
				resp.sendRedirect("user-table");
			} else
			{
				req.setAttribute("errorMessage", "Cập nhật người dùng thất bại");
				req.setAttribute("user", users);
				req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
				
			}
			
			
			
			break;
		}
		case "/user-delete":{
			
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
		
		
		
		
	}
	
}

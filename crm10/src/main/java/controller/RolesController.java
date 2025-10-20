package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Roles;
import services.RolesServices;
@WebServlet(name ="rolesController", urlPatterns = {"/role-table","/role-add","/role-edit","/role-delete"})
public class RolesController extends HttpServlet {
	RolesServices rolesServices = new RolesServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		List<Roles> rolesList = rolesServices.getAllRoles();
		
		String path = req.getServletPath();
		
		switch (path) {
		case "/role-table": {
			req.setAttribute("rolesList", rolesList);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		}
		case "/role-add": {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			
			break;
		}
		case "/role-edit": {
			
			int id = Integer.parseInt(req.getParameter("id"));
			Roles roles = rolesServices.findRolesById(id);
			req.setAttribute("role", roles);
			
			
			
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
			
			break;
		}
		case "/role-delete" :{
			int id = Integer.parseInt(req.getParameter("id"));
			rolesServices.deleteRoles(id);
			resp.sendRedirect("role-table");
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
		case "/role-add": {
			String rolename = req.getParameter("rolename");
			String roledescription = req.getParameter("roledescription");
			
			Roles roles = new Roles();
			roles.setName(rolename);
			roles.setDescription(roledescription);
			
			boolean result = rolesServices.addRoles(roles);
			
			if(result)
			{
				resp.sendRedirect("role-table");
				
			}
			else
			{
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
				
			}
			break;
		}
		case "/role-edit":{
			int id = Integer.parseInt(req.getParameter("id"));
			String rolename = req.getParameter("rolename");
			String roledescription = req.getParameter("roledescription");
			Roles roles = new Roles();
			roles.setId(id);
			roles.setName(rolename);
			roles.setDescription(roledescription);
			
			if(rolesServices.updateRoles(roles))
			{
				resp.sendRedirect("role-table");
			}
			else
			{
				req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
				
			}
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
}

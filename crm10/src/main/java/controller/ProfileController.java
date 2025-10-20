package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileDTO;
import entity.TaskProgress;
import entity.Users;
import services.ProfileServices;
import services.TaskProgressServices;
import services.UsersServices;

@WebServlet(name = "profileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
	
	 private UsersServices usersServices = new UsersServices();
	 private ProfileServices profileServices = new  ProfileServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        int id = Integer.parseInt(idParam);
        Users user = usersServices.findById(id);
        TaskProgress taskProgress =  profileServices.findTaskByUserId(id);
        List<ProfileDTO> listProfileDTO = profileServices.findTaskProjectByUserId(id);
        if (user == null) {
            resp.sendRedirect("404.jsp");
            return;
        }

        req.setAttribute("userProfile", user);
        req.setAttribute("taskProgress", taskProgress);
        req.setAttribute("listProfileDTO", listProfileDTO);
        
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
	}
}

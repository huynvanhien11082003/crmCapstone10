package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Users;
import services.UsersServices;

@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private UsersServices userServices = new UsersServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {

			Users user = (Users) session.getAttribute("user");
			String contextPath = req.getContextPath();
			if ("admin".equals(user.getRolesid().getName())) {
				resp.sendRedirect(contextPath + "/admin/dashboard.jsp");
			} else if ("leader".equals(user.getRolesid().getName())) {
				resp.sendRedirect(contextPath + "/leader/dashboard.jsp");
			} else {
				resp.sendRedirect(contextPath + "/member/dashboard.jsp");
			}
			return;
		}

		req.getRequestDispatcher("login.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("email");
		String password = req.getParameter("password");

		
		Users user = userServices.login(username, password);

		
		if (user == null) {
			req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}

		
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
	
		String roleName = user.getRolesid().getName();
		String contextPath = req.getContextPath();

		if ("ROLE_ADMIN".equalsIgnoreCase(roleName)) {
			resp.sendRedirect(contextPath + "/admin/dashboard.jsp");
		} else if ("ROLE_LEADER".equalsIgnoreCase(roleName)) {
			resp.sendRedirect(contextPath + "/leader/dashboard.jsp");
		} else if ("ROLE_MEMBER".equalsIgnoreCase(roleName)) {
			resp.sendRedirect(contextPath + "/member/dashboard.jsp");
		} else {
			
			req.setAttribute("error", "Không xác định được vai trò người dùng!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}

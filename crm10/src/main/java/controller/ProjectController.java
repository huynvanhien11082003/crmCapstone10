package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProjectMemberDTO;
import entity.Projects;
import services.ProjectsMemberServices;
import services.ProjectsServices;

@WebServlet(name = "ProjectController", urlPatterns = { "/groupwork", "/groupwork-add", "/groupwork-edit",
		"/groupwork-delete", "/groupwork-detail" })
public class ProjectController extends HttpServlet {

	ProjectsServices projectsServices = new ProjectsServices();
	ProjectsMemberServices projectMemberServices = new ProjectsMemberServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Projects> projectsList = projectsServices.getAllProjects();
		String path = req.getServletPath();

		switch (path) {
		case "/groupwork": {
			req.setAttribute("projectList", projectsList);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);

			break;
		}
		case "/groupwork-add": {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);

			break;
		}
		case "/groupwork-edit": {
			int id = Integer.parseInt(req.getParameter("id"));
			Projects projects = projectsServices.findById(id);
			req.setAttribute("project", projects);
			req.getRequestDispatcher("groupwork-edit.jsp").forward(req, resp);

			break;
		}
		case "/groupwork-delete": {
			int id = Integer.parseInt(req.getParameter("id"));
			projectsServices.deleteProjects(id);
			resp.sendRedirect("groupwork");

			break;
		}
		case "/groupwork-detail": {
			int id = Integer.parseInt(req.getParameter("id"));
			Projects projects = projectsServices.findById(id);
			List<ProjectMemberDTO> projectMemberDTOs = projectMemberServices.getDetailsByProjectId(id);

			long totalTasks = projectMemberDTOs.size();
			long notStarted = projectMemberDTOs.stream().filter(pm -> pm.getStatus().equalsIgnoreCase("Chưa bắt đầu"))
					.count();
			long inProgress = projectMemberDTOs.stream().filter(pm -> pm.getStatus().equalsIgnoreCase("Đang thực hiện"))
					.count();
			long completed = projectMemberDTOs.stream().filter(pm -> pm.getStatus().equalsIgnoreCase("Hoàn thành"))
					.count();

			int percentNotStarted = totalTasks == 0 ? 0 : (int) (notStarted * 100 / totalTasks);
			int percentInProgress = totalTasks == 0 ? 0 : (int) (inProgress * 100 / totalTasks);
			int percentCompleted = totalTasks == 0 ? 0 : (int) (completed * 100 / totalTasks);
			req.setAttribute("project", projects);
			req.setAttribute("projectMembers", projectMemberDTOs);
			req.setAttribute("percentNotStarted", percentNotStarted);
			req.setAttribute("percentInProgress", percentInProgress);
			req.setAttribute("percentCompleted", percentCompleted);
			
			req.getRequestDispatcher("groupwork-detail.jsp").forward(req, resp);
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case "/groupwork-add": {
			String name = req.getParameter("nameproject");
			String startdate = req.getParameter("startdate");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(startdate, formatter);
			LocalDateTime startDateTime = date.atStartOfDay();

			String enddate = req.getParameter("enddate");
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dateEnd = LocalDate.parse(enddate, formatter2);
			LocalDateTime endDate = dateEnd.atStartOfDay();
			Projects projects = new Projects();
			projects.setName(name);
			projects.setStartdate(startDateTime);
			projects.setEnddate(endDate);

			projectsServices.addProjects(projects);
			resp.sendRedirect("groupwork");

			break;
		}
		case "/groupwork-edit": {

			String id = req.getParameter("projectIds");
			String name = req.getParameter("name");
			String startDateStr = req.getParameter("startdate");
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, dateTimeFormatter);
			String endDateStr = req.getParameter("enddate");
			LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, dateTimeFormatter);
			Projects projects = new Projects();
			projects.setId(Integer.parseInt(id));
			projects.setName(name);
			projects.setStartdate(startDateTime);
			projects.setEnddate(endDateTime);

			projectsServices.updateProjects(projects);

			resp.sendRedirect("groupwork");

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

}

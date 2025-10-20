package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Projects;
import entity.TaskProgress;
import entity.Tasks;
import entity.Users;
import services.ProjectsServices;
import services.TaskProgressServices;
import services.TasksServices;
import services.UsersServices;

@WebServlet(name = "taskProgressController", urlPatterns = {"/task","/task-add","/task-delete","/task-edit"})
public class TaskProgressController extends HttpServlet {
	
	TaskProgressServices progressServices = new TaskProgressServices();
	ProjectsServices projectsServices = new ProjectsServices();
	UsersServices userServices =  new UsersServices();
	TasksServices tasksServices = new TasksServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		switch (path) {
		case "/task": {
			List<TaskProgress> list  = progressServices.findAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			
			break;
		}
		case "/task-add": {
			List<Users> userList = userServices.getAllUsers();
		    List<Projects> projectList = projectsServices.getAllProjects();
		    List<Tasks> taskList = tasksServices.findAllTasks();

		    req.setAttribute("userList", userList);
		    req.setAttribute("projectList", projectList);
		    req.setAttribute("taskList", taskList);

		    req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			
			break;
		}
		case "/task-delete": {
		    String idStr = req.getParameter("id");
		    int id = Integer.parseInt(idStr);

		    boolean success = progressServices.deleteTaskProgress(id);
		    if (success) {
		        resp.sendRedirect("task");
		    }
		    break;
		}
		case "/task-edit": {
            int id = Integer.parseInt(req.getParameter("id"));
            TaskProgress tp = progressServices.findById(id);

            if (tp == null) {
                resp.getWriter().println("Không tìm thấy Task Progress!");
                return;
            }

            List<Users> userList = userServices.getAllUsers();
            List<Projects> projectList = projectsServices.getAllProjects();
            List<Tasks> taskList = tasksServices.findAllTasks();

            req.setAttribute("taskProgress", tp);
            req.setAttribute("userList", userList);
            req.setAttribute("projectList", projectList);
            req.setAttribute("taskList", taskList);

            req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
            break;
        }
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		switch (path) {
		case "/task-add": {
			int projectId = Integer.parseInt(req.getParameter("projectid"));
		    int taskId = Integer.parseInt(req.getParameter("taskid"));
		    int userId = Integer.parseInt(req.getParameter("userid"));
		    String startDate = req.getParameter("startdate");
		    String endDate = req.getParameter("enddate");

		    boolean success = progressServices.addTaskProgress(projectId, taskId, userId, startDate, endDate);
		    if (success) {
		        resp.sendRedirect("task");
		    } 
			break;
		}
		case "/task-edit": {
            int id = Integer.parseInt(req.getParameter("id"));
            int projectId = Integer.parseInt(req.getParameter("projectid"));
            int taskId = Integer.parseInt(req.getParameter("taskid"));
            int userId = Integer.parseInt(req.getParameter("userid"));
            String startDateStr = req.getParameter("startDate");
            String endDateStr = req.getParameter("endDate");
            String status = req.getParameter("status");
            int progressPercent = Integer.parseInt(req.getParameter("progresspercent"));

            boolean success = progressServices.updateTaskProgress(
                    id, projectId, taskId, userId, startDateStr, endDateStr, status, progressPercent
            );

            if (success) {
                resp.sendRedirect("task");
            } else {
                resp.getWriter().println("Cập nhật thất bại!");
            }
            break;
        }

		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
}

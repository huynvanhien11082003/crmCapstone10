package services;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import entity.Projects;
import entity.TaskProgress;
import entity.Tasks;
import entity.Users;
import repository.TaskProgressRepository;

public class TaskProgressServices {
	TaskProgressRepository progressRepository = new TaskProgressRepository();

	public List<TaskProgress> findAll() {
		return progressRepository.findAll();
	}

	public boolean addTaskProgress(int projectId, int taskId, int userId, String startDateStr, String endDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime startDate = LocalDateTime.parse(startDateStr + " 00:00:00", formatter);
		LocalDateTime endDate = LocalDateTime.parse(endDateStr + " 00:00:00", formatter);

		Timestamp start = Timestamp.valueOf(startDate);
		Timestamp end = Timestamp.valueOf(endDate);

		int result = progressRepository.insertTaskProgress(taskId, userId, start, end);
		return result > 0;
	}

	public boolean deleteTaskProgress(int id) {
		int result = progressRepository.deleteTaskProgress(id);
		return result > 0;
	}

	public boolean updateTaskProgress(int id, int projectId, int taskId, int userId,
	        String startDateStr, String endDateStr, String status, int progressPercent) {
	    try {
	        
	        LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
	        LocalDateTime endDate = LocalDate.parse(endDateStr).atStartOfDay();

	        TaskProgress taskProgress = new TaskProgress();
	        taskProgress.setId(id);
	        taskProgress.setStartDate(startDate);
	        taskProgress.setEndDate(endDate);
	        taskProgress.setStatus(status);
	        taskProgress.setProgressPercent(progressPercent);

	        Tasks task = new Tasks();
	        task.setId(taskId);
	        Projects project = new Projects();
	        project.setId(projectId);
	        task.setProjectsid(project);

	        Users user = new Users();
	        user.setId(userId);

	        taskProgress.setTaskid(task);
	        taskProgress.setUsersid(user);

	        int result = progressRepository.updateTaskProgress(taskProgress);
	        return result > 0;
	    } catch (Exception e) {
	        System.out.println("❌ Lỗi khi cập nhật TaskProgress: " + e.getMessage());
	        return false;
	    }
	}

	 public TaskProgress findById(int id)
	 {
		 return progressRepository.findById(id);
	 }
}

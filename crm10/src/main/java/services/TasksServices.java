package services;

import java.util.List;

import entity.Tasks;
import repository.TaskRepository;

public class TasksServices {
	
	TaskRepository repository = new TaskRepository();
	public List<Tasks> findAllTasks() {
		return repository.findAllTasks();
	}
}

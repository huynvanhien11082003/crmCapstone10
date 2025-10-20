package services;

import java.util.List;

import entity.Projects;
import repository.ProjectRepository;

public class ProjectsServices {
	private ProjectRepository projectRepository = new ProjectRepository();
	
	public List<Projects> getAllProjects()
	{
		return projectRepository.findAll();
	}
	public boolean addProjects(Projects projects)
	{
		return projectRepository.addProject(projects);
	}
	public Projects findById(int id)
	{
		return projectRepository.findById(id);
	}
	
	public boolean updateProjects(Projects projects)
	{
		return projectRepository.updateProject(projects);
	}
	public boolean deleteProjects(int id)
	{
		return projectRepository.deleteProject(id);
	}
}

package services;

import java.util.List;

import dto.ProjectMemberDTO;
import repository.ProjectMemberRepository;

public class ProjectsMemberServices {
	private ProjectMemberRepository projectMemberRepository = new ProjectMemberRepository();
	
	
	
	public List<ProjectMemberDTO> getDetailsByProjectId(int id)
	{
		return projectMemberRepository.getDetailsByProjectId(id);
	}
}

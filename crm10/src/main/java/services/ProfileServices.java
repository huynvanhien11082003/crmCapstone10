package services;

import java.util.List;

import dto.ProfileDTO;
import entity.TaskProgress;
import entity.Users;
import repository.ProfileRepository;

public class ProfileServices {
	private ProfileRepository  profileRepository = new ProfileRepository();
	
	public Users getUserById(int id)
	{
		return profileRepository.findById(id);
	}
	public TaskProgress findTaskByUserId(int id)
	{
		return profileRepository.findTaskByUserId(id);
	}
	public List<ProfileDTO> findTaskProjectByUserId(int  id)
	{
		return profileRepository.findTaskProjectByUserId(id);
	}
}

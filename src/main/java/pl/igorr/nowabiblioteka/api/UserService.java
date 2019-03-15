package pl.igorr.nowabiblioteka.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.igorr.nowabiblioteka.domain.UserDTO;
import pl.igorr.nowabiblioteka.domain.UsersView;

@Repository
public interface UserService {
	
	public void createUser(UserDTO user);
	public UserDTO getUser(String username);
	public void updateUser(UserDTO user);
	public List<UsersView> listUsers();
	

}

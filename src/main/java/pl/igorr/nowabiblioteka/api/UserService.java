package pl.igorr.nowabiblioteka.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.igorr.nowabiblioteka.domain.UsersView;

@Repository
public interface UserService {
	
	public void createUserJDBC(UsersView user);
	public boolean checkPassword (String username, String password);
	public List<UsersView> listUsers();
	

}

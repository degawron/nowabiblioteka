package pl.igorr.nowabiblioteka.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.igorr.nowabiblioteka.api.UserService;
import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.UsersView;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Service
public class UserServiceImpl implements UserService {

	private LibraryDAO libraryDAO;

	@Autowired
	public void setLibraryDAO(LibraryDAO libraryDAO) { // wstrzyknięcie beana z interfejsu LibraryDAO
		this.libraryDAO = libraryDAO;
	}

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) { // wstrzyknięcie beana DataSource z PersistenceConfig
		this.dataSource = dataSource;
	}

	@Override
	public void createUserJDBC(UsersView user) {
		JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource); // utworzenie menagera użytkowników Springa
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); //Utworzenie pustek kolekcji uprawnień
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //Utworzenie kodera haseł
		String listOfRoles = user.getAuthorities(); //przekazanie listy uprawnień do stringa
		if (listOfRoles == null) //jeżeli lista uprawnień jest pusta to wstawienie na nią "NONE"
			listOfRoles = "NONE";
		String[] roles = listOfRoles.split(","); //zamiana stringa na listę stringów (rozdzielenie po przecinku przekazywanym z formularza)

		for (String r : roles) {
			authorities.add(new SimpleGrantedAuthority(r)); //przepisanie uprawnień z listy stringów na kolekcję uprawnień przy użyciu SimpleGrantedAuthority
		}
		User newUser = new User(user.getUsername(), encoder.encode(user.getPassword()), true, true, true, true,
				authorities); //utworzenie obiektu no0wego użytkownika (to nie jest użytkownik z naszej domeny, tylko ze Spring Security)
		userManager.createUser(newUser); //zapisanie nowego użytkownika z bazie
	}

	@Override
	public boolean checkPassword(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsersView> listUsers() {
		List<UsersView> users = (List<UsersView>) libraryDAO.list("from UsersView"); //wyświetlenie listy użytkowników
		return users;
	}

}

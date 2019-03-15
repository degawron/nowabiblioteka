package pl.igorr.nowabiblioteka.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.igorr.nowabiblioteka.api.UserService;
import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.UserDTO;
import pl.igorr.nowabiblioteka.domain.UsersView;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Service
public class UserServiceImpl implements UserService {
	
	private LibraryDAO libraryDAO;
	private DataSource dataSource;
	
	@Autowired
	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) { // wstrzyknięcie beana DataSource z PersistenceConfig
		this.dataSource = dataSource;
	}

	//private JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource); // utworzenie menagera użytkowników Springa
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //Utworzenie kodera haseł

	
	@Override
	public void createUser(UserDTO user) {
		JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource); // utworzenie menagera użytkowników Springa
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //Utworzenie kodera haseł
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); //Utworzenie nowej kolekcji uprawnień
		if (user.getAuthorities().length==0) {
			authorities.add(new SimpleGrantedAuthority("NONE")); //jeżeli nie wybrano żadnej roli to uprawnienie ustaw na NONE
		}
		else {
			for (String a : user.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority(a)); //przepisanie uprawnień z listy stringów na kolekcję uprawnień przy użyciu SimpleGrantedAuthority
			}	
		}
		User newUser = new User(user.getUsername(), encoder.encode(user.getPassword()), true, true, true, true,authorities); // Utworzenie nowego obiektu User
		userManager.createUser(newUser); //zapisanie nowego użytkownika z bazie
	}
	
	@Override
	public UserDTO getUser(String username) {
		JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource); // utworzenie managera użytkowników Springa
		User savedUser = (User) userManager.loadUserByUsername(username); //odczyt danych użytkownika z bazy
		Set<String> authoritySet = AuthorityUtils.authorityListToSet(savedUser.getAuthorities()); //konwersja uprawnień na Set stringów
		String[] authorities = authoritySet.toArray(new String[authoritySet.size()]); //zamiana Seta stringów na tablicę
		return new UserDTO(savedUser.getUsername(), null, null, savedUser.isEnabled(), authorities); //zwrócenie obiektu UserDTO
	}
	
	@Override
	public void updateUser(UserDTO user) {
		JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource); // utworzenie managera użytkowników Springa
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); //Utworzenie nowej kolekcji uprawnień
		
		if (user.getAuthorities().length==0) {
			authorities.add(new SimpleGrantedAuthority("NONE")); //jeżeli nie wybrano żadnej roli to uprawnienie ustaw na NONE
		}
		else {
			for (String a : user.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority(a)); //przepisanie uprawnień z listy stringów na kolekcję uprawnień przy użyciu SimpleGrantedAuthority
			}	
		}			
		String password = userManager.loadUserByUsername(user.getUsername()).getPassword(); //odczyt hasła użytkownika z bazy
		if (!user.getPassword().isEmpty()) password = encoder.encode(user.getPassword()); //jeśli nowe hasło nie jest puste to podmień na nowe hasło
		
		User updatedUser = new User(user.getUsername(), password, user.getEnabled(), true, true, true,authorities); // Utworzenie nowego obiektu User z danymi przekazanymi do metody
				
		userManager.updateUser(updatedUser); //zapisanie zmienionych danych użytkownika z bazie		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsersView> listUsers() {
		List<UsersView> users = (List<UsersView>) libraryDAO.list("from UsersView"); //wyświetlenie listy użytkowników
		return users;
	}

}

package pl.igorr.nowabiblioteka.validation;

import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class UniqueUserValidator implements ConstraintValidator<UniqueUserConstraint, String>{

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
    public void initialize(UniqueUserConstraint uniqueUser) {  //inicjalizacja walidatora
    }
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) { //metoda definiująca warunki walidacji
		JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource); //utworzenie nowego menagera użytkowników 
		try {
		userManager.loadUserByUsername(username); //próba pobrania użytkownika o podanej nazwie (loginie)
		}
		catch (UsernameNotFoundException e){ //w razie niepoodwodzenia wynik walidacji pozytywny (użytkownik nie istnieje)
			return true;			
		}
		return false; //jeśli użytkownika udało się pobrać wynik walidacji negatywny
	}

}

package pl.igorr.nowabiblioteka.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.provisioning.JdbcUserDetailsManager;

import pl.igorr.nowabiblioteka.api.ReaderService;
import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.domain.ReadersView;

public class DBDAOServiceTest {
	
	public static void main(String[] args) {
	
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(pl.igorr.nowabiblioteka.config.RootConfig.class);
		
		LibraryDAO library = (LibraryDAO) ctx.getBean("libraryDAOImpl");
		
		Reader reader = (Reader) library.get(Reader.class, 3);
		System.out.println(reader.toString());
		
		@SuppressWarnings("unchecked")
		List<Reader> list = (List<Reader>) library.list("from Reader");
		System.out.println("DAO:" + list.toString());
		
		ReaderService readerService = (ReaderService) ctx.getBean("readerServiceImpl");
		
		List<ReadersView> list2= readerService.listReaders();
		System.out.println("Service:" + list2.toString());
		
		
		JdbcUserDetailsManager userDetailManager = (JdbcUserDetailsManager) ctx.getBean("jdbcUserDetailsManager");
		Collection<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>();
		grantedAuth.add(null);
		User newUser = new User("test","test",true,true,true,true,grantedAuth);
		userDetailManager.createUser(newUser);
			
		ctx.close();
				
	}
	

	    
}

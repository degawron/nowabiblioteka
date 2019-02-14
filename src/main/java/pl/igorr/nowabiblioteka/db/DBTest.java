package pl.igorr.nowabiblioteka.db;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.igorr.nowabiblioteka.domain.Readers;

public class DBTest {
	
	public static void main(String[] args) {
	
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(pl.igorr.nowabiblioteka.config.RootConfig.class);
		
		LibraryDAO readersDAO = (LibraryDAO) ctx.getBean("czytelnicyDAO");
		
		List<Readers> list = readersDAO.listReaders();
		
		ctx.close();
		
		System.out.println(list.toString());
	}
	

	    
}

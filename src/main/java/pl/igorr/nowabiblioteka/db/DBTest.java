package pl.igorr.nowabiblioteka.db;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.igorr.nowabiblioteka.domain.Reader;

public class DBTest {
	
	public static void main(String[] args) {
	
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(pl.igorr.nowabiblioteka.config.RootConfig.class);
		
		LibraryDAO library = (LibraryDAO) ctx.getBean("libraryDAO");
		
		List<Reader> list = library.listReaders();
		
				
		System.out.println(list.toString());
		
		System.out.println("Ostatni czytelnik: "+library.getLastReader().getFirstName()+" "+library.getLastReader().getLastName());

		ctx.close();
		
	}
	

	    
}

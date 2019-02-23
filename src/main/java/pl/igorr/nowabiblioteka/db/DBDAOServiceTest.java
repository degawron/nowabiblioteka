package pl.igorr.nowabiblioteka.db;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		
		ReaderService libraryService = (ReaderService) ctx.getBean("libraryServiceImpl");
		
		List<ReadersView> list2= libraryService.listReaders();
		System.out.println("Service:" + list2.toString());
			
		ctx.close();
		
	}
	

	    
}

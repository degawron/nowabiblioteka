package pl.igorr.nowabiblioteka.tests;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.Books;
import pl.igorr.nowabiblioteka.domain.Borrowings;
import pl.igorr.nowabiblioteka.domain.Readers;
import pl.igorr.nowabiblioteka.web.BooksController;
import pl.igorr.nowabiblioteka.web.BorrowingsController;
import pl.igorr.nowabiblioteka.web.ReadersController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=pl.igorr.nowabiblioteka.config.RootConfig.class)
public class ViewsTest {
	
	@Autowired
	LibraryDAO library;

	@Test
	@Transactional
	@Rollback(true)
	public void shouldShowReaders() throws Exception {
		List<Readers> expectedReaders = library.listReaders(); //pobranie listy czytelników z bazy

		ReadersController controller = new ReadersController(library); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/readers.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/readers")) // wywołanie widoku czytelników
				.andExpect(view().name("readers")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("readersList")) // ... z własciwym modelem ...
				.andExpect(model().attribute("readersList", hasItems(expectedReaders.toArray()))); // ... i elementami (hasItemsz hamcrest-a)
	}

	@Test
	@Transactional
	@Rollback(true)
	public void shouldShowBooks() throws Exception {
		List<Books> expectedBooks = library.listBooks(); //pobranie listy książek z bazy

		BooksController controller = new BooksController(library); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/books.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/books")) // wywołanie widoku czytelników
				.andExpect(view().name("books")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("booksList")) // ... z własciwym modelem ...
				.andExpect(model().attribute("booksList", hasItems(expectedBooks.toArray()))); // ... i elementami (hasItemsz hamcrest-a)
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void shouldShowBorrowings() throws Exception {
		List<Borrowings> expectedBorrowings = library.listBorrowings(); //pobranie listy wypożyczeń z bazy

		BorrowingsController controller = new BorrowingsController(library); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/borrowings.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/borrowings")) // wywołanie widoku czytelników
				.andExpect(view().name("borrowings")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("borrowingsList")) // ... z własciwym modelem ...
				.andExpect(model().attribute("borrowingsList", hasItems(expectedBorrowings.toArray()))); // ... i elementami (hasItemsz hamcrest-a)
	}
	

}

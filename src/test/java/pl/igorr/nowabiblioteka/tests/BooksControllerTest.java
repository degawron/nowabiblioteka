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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.Book;
import pl.igorr.nowabiblioteka.web.BooksController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=pl.igorr.nowabiblioteka.config.RootConfig.class)
public class BooksControllerTest {
	
	@Autowired
	LibraryDAO library;

	@Test
	@Transactional
	@Rollback(true)
	public void shouldShowBooks() throws Exception {
		List<Book> expectedBooks = library.listBooks(); //pobranie listy książek z bazy

		BooksController controller = new BooksController(library); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/books.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/books")) // wywołanie widoku czytelników
				.andExpect(view().name("books")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("bookList")) // ... z własciwym modelem ...
				.andExpect(model().attribute("bookList", hasItems(expectedBooks.toArray()))); // ... i elementami (hasItemsz hamcrest-a)
	}
	
	@Test
	public void testAddBookPage() throws Exception {
		BooksController controller = new BooksController(library);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
		mockMvc.perform(get("/books/add"))
				.andExpect(view().name("bookForm")); // sprawdzamy czy wywołanie strony dodawania książki wyświetla właściwy widok
	}

}

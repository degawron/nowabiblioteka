package pl.igorr.nowabiblioteka.tests;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;import org.springframework.web.servlet.view.InternalResourceView;

import pl.igorr.nowabiblioteka.api.BookService;
import pl.igorr.nowabiblioteka.domain.Book;
import pl.igorr.nowabiblioteka.web.BooksController;


public class BooksControllerTest {
	
	@Mock
	BookService mockBookService;

	@Before
	public void setUp() throws Exception {  //inicjalizacja mock-ów
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldShowBooks() throws Exception {
		
		when(mockBookService.listBooks()).thenReturn(fakeBooksList(10)); //zamockowanie listy zwracanej przez listReaders
		List<Book> expectedBooks = mockBookService.listBooks(); //pobranie listy książek z bazy

		BooksController controller = new BooksController(mockBookService); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/books.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/books")) // wywołanie widoku czytelników
				.andExpect(view().name("books")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("bookList")) // ... z własciwym modelem ...
				.andExpect(model().attribute("bookList", hasItems(expectedBooks.toArray()))); // ... i elementami (hasItems z hamcrest-a)
	}
	
	@Test
	public void testAddBookPage() throws Exception {
		BooksController controller = new BooksController(mockBookService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
		mockMvc.perform(get("/books/add"))
				.andExpect(view().name("bookForm")); // sprawdzamy czy wywołanie strony dodawania książki wyświetla właściwy widok
	}

	// TODO testy formularzy dodawania i edycji książek
	
	public List<Book> fakeBooksList(int size){ //metoda tworząca listę książek o zadanej długości
		List<Book> list = new ArrayList<Book>();
		for (int i=0; i < size; i++) {
			list.add(new Book("Tytuł "+i,"Autor "+i,2000+i,10+i));
		}
		return list;
	}
}

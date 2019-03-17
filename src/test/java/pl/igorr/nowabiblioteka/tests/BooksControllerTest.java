package pl.igorr.nowabiblioteka.tests;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
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
				.andExpect(model().attributeExists("bookList")) // ... z właściwym modelem ...
				.andExpect(model().attribute("bookList", hasItems(expectedBooks.toArray()))); // ... i elementami (hasItems z hamcrest-a)
	}
	
	@Test
	public void shouldShowAddBookPage() throws Exception {
		BooksController controller = new BooksController(mockBookService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
		mockMvc.perform(get("/books/add"))
				.andExpect(view().name("bookForm")); // sprawdzamy czy wywołanie strony dodawania książki wyświetla właściwy widok
	}
	
	@Test
	public void shouldAddUser() throws Exception {
		Book book = new Book("Zażółć","Gęśląjaźń",2000,5); //utworzenie wzorcowej książki	
		BooksController controller = new BooksController(mockBookService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
		mockMvc.perform(post("/books/add") //wywołanie dodania książki z parametrami odpowiadającymi książce wzorcowej
				.param("title","Zażółć")
				.param("author","Gęśląjaźń")
				.param("year","2000")
				.param("quantity","5"))
			.andExpect(redirectedUrl("/books")); //sprawdzenie przekierowania
		verify(mockBookService, atLeastOnce()).addBook(book); //sprawdzenie czy byłą wywołana metoda dodająca książkę wzorcową
	}
	
	@Test
	public void shouldShowEditBookPage() throws Exception {
		Book book = new Book("Zażółć","Gęśląjaźń",2000,5); //utworzenie wzorcowej książki
		when(mockBookService.getBook(0)).thenReturn(book); //ustalamy zwrócenie książki wzorcowej przy wywołaniu edycji ksiąski id=0
		BooksController controller = new BooksController(mockBookService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/books/edit/0"))  //wywołanie edycji książki nr 0
				.andExpect(view().name("bookForm")) //sprawdzenie nazwy zwróconego widoku...
				.andExpect(model().attributeExists("book")) //...obecności modelu book...
				.andExpect(model().attribute("book", book)); //...oraz zgodności modelu z ze wzorem 
	}

	@Test
	public void shouldEditUser() throws Exception {
		Book book = new Book("Zażółć","Gęśląjaźń",2000,5); //utworzenie wzorcowej książki	
		BooksController controller = new BooksController(mockBookService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
		mockMvc.perform(post("/books/edit/0") //wywołanie edycji książki z parametrami odpowiadającymi książce wzorcowej
				.param("title","Zażółć")
				.param("author","Gęśląjaźń")
				.param("year","2000")
				.param("quantity","5"))
			.andExpect(redirectedUrl("/books")); //sprawdzenie przekierowania
		verify(mockBookService, atLeastOnce()).updateBook(book); //sprawdzenie czy byłą wywołana metoda edytująca książkę wzorcową
	}
	
	public List<Book> fakeBooksList(int size){ //metoda tworząca listę książek o zadanej długości
		List<Book> list = new ArrayList<Book>();
		for (int i=0; i < size; i++) {
			list.add(new Book("Tytuł "+i,"Autor "+i,2000+i,10+i));
		}
		return list;
	}
}

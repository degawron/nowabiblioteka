package pl.igorr.nowabiblioteka.tests;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import pl.igorr.nowabiblioteka.api.BookService;
import pl.igorr.nowabiblioteka.api.BorrowService;
import pl.igorr.nowabiblioteka.api.ReaderService;
import pl.igorr.nowabiblioteka.domain.Book;
import pl.igorr.nowabiblioteka.domain.Borrow;
import pl.igorr.nowabiblioteka.domain.BorrowsView;
import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.web.BorrowsController;


public class BorrowsControllerTest {
	
	@Mock
	BorrowService mockBorrowService;
	
	@Mock
	ReaderService mockReaderService;
	
	@Mock
	BookService mockBookService;

	@Before
	public void setUp() throws Exception { //inicjalizacja mock-ów
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldShowBorrowings() throws Exception {
		
		when(mockBorrowService.listBorrows()).thenReturn(fakeBorrowsList(10)); //ustalenie jaką listę wypożyczeń będzie zwracał mock
		List<BorrowsView> expectedBorrowings = mockBorrowService.listBorrows(); //wygenerowanie spodziewanej listy wypożyczeń

		BorrowsController controller = new BorrowsController(mockBorrowService, mockReaderService, mockBookService); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/borrowings.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/borrows")) // wywołanie widoku czytelników
				.andExpect(view().name("borrows")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("borrowsViewList")) // ... z właściwym modelem ...
				.andExpect(model().attribute("borrowsViewList", hasItems(expectedBorrowings.toArray()))); // ... i elementami (hasItemsz hamcrest-a)
	}
	
	@Test
	public void shouldShowNewBorrowForm() throws Exception{
		List<Reader> expectedReaders = fakeReadersList(10); //stworzenie list testowych czytelników
		List<Book> expectedBooks = fakeBooksList(10); //stworzenie listy testowych książek
		when(mockReaderService.listActiveReaders()).thenReturn(expectedReaders); //ustalenie listy czytelników zwracanej przez metodę
		when(mockBookService.listAvailableBooks()).thenReturn(expectedBooks); //ustalenie listy książek zwracanej przez metodę
		BorrowsController controller = new BorrowsController(mockBorrowService, mockReaderService, mockBookService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/borrows/new")) //sprawdzenie czy wywołanie dodania nowego wypożyczenia
			.andExpect(view().name("borrowForm")) //zwróci właściwy widok
			.andExpect(model().attributeExists("readerList"))  //zawierający właściwe dane w modelu
			.andExpect(model().attribute("readerList", hasItems(expectedReaders.toArray())))
			.andExpect(model().attributeExists("bookList"))
			.andExpect(model().attribute("bookList", hasItems(expectedBooks.toArray())));
			
	}
	
	@Test
	public void shouldCreateNewBorrow() throws Exception{
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01"); //przetworzenie stringa na datę
		Borrow borrow = new Borrow(new Book(),new Reader(),date,7,null); //utworzenie wzorcowego wypożyczenia (czytelnik i książka dostaną id=0 z pustego konstruktora)
		BorrowsController controller = new BorrowsController(mockBorrowService, mockReaderService, mockBookService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/borrows/new") //wywołanie dodania wypożyczenia z parametrami odpowiadającymi testowemu wypożyczeniu
				.param("reader.id", "0")
				.param("book.id", "0")
				.param("dateOfBorrow", "2019-01-01")
				.param("term", "7"))
			.andExpect(redirectedUrl("/borrows")); //sprawdzenie zwracanego przekierowania...
		verify(mockBorrowService, atLeastOnce()).borrowBook(borrow); //...oraz wywołania metody dodającej wypożyczenie (Borrow musi mieć hashCode() i equals())
			
	}
	
	@Test
	public void shouldShowReturnForm() throws Exception {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01"); //przetworzenie stringa na datę
		Borrow borrow = new Borrow(new Book(),new Reader(),date,7,null); //utworzenie wzorcowego wypożyczenia (czytelnik i książka dostaną id=0 z pustego konstruktora)
		when(mockBorrowService.getBorrow(0)).thenReturn(borrow); //ustalenie jakie wypożyczenie zwróci metoda dla id=0
		BorrowsController controller = new BorrowsController(mockBorrowService, mockReaderService, mockBookService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/borrows/return/0")) //wywołanie zwrotu z parametrami zgodnymi ze wzorem (id=0)
			.andExpect(view().name("returnBookForm")) //sprawdzenie nazwy zwracanego widoku
			.andExpect(model().attributeExists("borrow")) //..oraz obecności i wartości atrybutów modelu
			.andExpect(model().attribute("borrow", borrow))
			.andExpect(model().attributeExists("date"))
			.andExpect(model().attribute("date", LocalDate.now().toString()));
	}
	
	@Test
	public void shouldReturnBook() throws Exception {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01"); //przetworzenie stringa na datę
		Borrow borrow = new Borrow(null,null,null,0,date); //utworzenie wzorcowego wypożyczenia (zawiera tylko testową datę zwrotu)
		when(mockBorrowService.getBorrow(0)).thenReturn(borrow); //ustalenie jakie wypożyczenie zwróci metoda dla id=0
		BorrowsController controller = new BorrowsController(mockBorrowService, mockReaderService, mockBookService);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/borrows/return/0")
				.param("dateOfReturn", "2019-01-01"))
			.andExpect(redirectedUrl("/borrows"));
		verify(mockBorrowService, atLeastOnce()).returnBook(borrow);
	}
	
	public List<BorrowsView> fakeBorrowsList(int size){ //metoda generująca zadaną ilość wypożyczeń (klasy BorrowsView)
		List<BorrowsView> list = new ArrayList<BorrowsView>();
			for (int i=0; i<size; i++) {
				list.add(new BorrowsView(new Book(),new Reader(),null,i,null));
			}
		return list;
	}
	
	public List<Reader> fakeReadersList(int size) { // metoda tworząca listę podaną czytelników (klasy ReadersView)
		List<Reader> list = new ArrayList<Reader>();
		for (int i = 0; i < size; i++) {
			list.add(new Reader("Imię " + i, "Nazwisko " + i));
		}
		return list;
	}
	
	public List<Book> fakeBooksList(int size){ //metoda tworząca listę książek o zadanej długości
		List<Book> list = new ArrayList<Book>();
		for (int i=0; i < size; i++) {
			list.add(new Book("Tytuł "+i,"Autor "+i,2000+i,10+i));
		}
		return list;
	}


}

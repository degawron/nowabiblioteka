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
import org.springframework.web.servlet.view.InternalResourceView;

import pl.igorr.nowabiblioteka.api.BorrowService;
import pl.igorr.nowabiblioteka.domain.Book;
import pl.igorr.nowabiblioteka.domain.BorrowsView;
import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.web.BorrowsController;


public class BorrowsControllerTest {
	
	@Mock
	BorrowService mockBorrowService;

	@Before
	public void setUp() throws Exception { //inicjalizacja mock-ów
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldShowBorrowings() throws Exception {
		
		when(mockBorrowService.listBorrows()).thenReturn(fakeBorrowsList(10)); //ustalenie jaką listę wypożyczeń będzie zwracał mock
		List<BorrowsView> expectedBorrowings = mockBorrowService.listBorrows(); //wygenerowanie spodziewanej listy wypożyczeń

		BorrowsController controller = new BorrowsController(mockBorrowService, null, null); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/borrowings.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/borrows")) // wywołanie widoku czytelników
				.andExpect(view().name("borrows")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("borrowsViewList")) // ... z właściwym modelem ...
				.andExpect(model().attribute("borrowsViewList", hasItems(expectedBorrowings.toArray()))); // ... i elementami (hasItemsz hamcrest-a)
	}
	
	// TODO testy nowego wypożyczenia i zwrotu książki
	
	public List<BorrowsView> fakeBorrowsList(int size){ //metoda generująca zadaną ilość wypożyczeń (klasy BorrowsView)
		List<BorrowsView> list = new ArrayList<BorrowsView>();
			for (int i=0; i<size; i++) {
				list.add(new BorrowsView(new Book(),new Reader(),null,i,null));
			}
		return list;
	}

}

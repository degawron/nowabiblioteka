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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import pl.igorr.nowabiblioteka.api.ReaderService;
import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.domain.ReadersView;
import pl.igorr.nowabiblioteka.web.ReadersController;

public class ReadersControllerTest {

	@Mock
	private ReaderService mockReaderService;

	@Before
	public void setUp() throws Exception {  //inicjalizacja mock-ów
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldShowReaders() throws Exception {

		when(mockReaderService.listReaders()).thenReturn(fakeReadersList(10)); //zamockowanie listy zwracanej przez listReaders
		List<ReadersView> expectedReaders = mockReaderService.listReaders(); // pobranie listy czytelników z bazy
		ReadersController controller = new ReadersController(mockReaderService); // nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/readers.jsp"))
				.build(); // utworzenie mocka MVC

		mockMvc.perform(get("/readers")) // wywołanie widoku czytelników
				.andExpect(view().name("readers")) // sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("readersViewList")) // ... z właściwym modelem ...
				.andExpect(model().attribute("readersViewList", hasItems(expectedReaders.toArray()))); // ... i
																										// elementami
																										// (hasItems z
																										// hamcrest-a)
	}

	@Test
	public void shouldShowAddReaderPage() throws Exception {
		ReadersController controller = new ReadersController(mockReaderService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();

		mockMvc.perform(get("/readers/add"))
				.andExpect(view().name("readerForm")); // sprawdzamy czy zwracany jest właściwy widok
		
	}

	@Test
	public void shouldAddReader() throws Exception {
		Reader reader = new Reader("Zażółć", "Gęśląjazń"); // tworzymy testowego czytelnika
		ReadersController controller = new ReadersController(mockReaderService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
		
		mockMvc.perform(post("/readers/add") // mockujemy wywołanie dodawania czytelnika z zadanym imieniem i nazwiskiem
				.param("firstName", "Zażółć")
				.param("lastName", "Gęśląjazń"))
		.andExpect(redirectedUrl("/readers"));// sprawdzamy czy nastąpiło przekierowanie
		verify(mockReaderService, atLeastOnce()).addReader(reader); // sprawdzenie czy została wywołana metoda
																			// addReader z danymi testowego czytelnika
	}
		
	@Test
	public void shouldShowEditReaderPage() throws Exception {
		Reader reader = new Reader("Zażółć", "Gęśląjazń"); // tworzymy testowego czytelnika
		when(mockReaderService.getReader(0)).thenReturn(reader); //ustawiamy zwrócenie czytelnika testowego przy wywołaniu edycji czytelnika id=0
		ReadersController controller = new ReadersController(mockReaderService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(get("/readers/edit/0"))
				.andExpect(view().name("readerForm")) // sprawdzamy czy wywołanie strony edycji czytelnika zwraca właściwy widok
				.andExpect(model().attributeExists("reader")) //... model ma atrybut reader....
				.andExpect(model().attribute("reader", reader)); //... i jest on równy testowemu czytelnikowi
	}	
	
	@Test
	public void shouldEditReader() throws Exception{
		Reader reader = new Reader("Zażółć", "Gęśląjaźń"); // tworzymy testowego czytelnika
		ReadersController controller = new ReadersController(mockReaderService);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(post("/readers/edit/0") //// mockujemy wywołanie dodawania czytelnika z zadanym imieniem i nazwiskiem
				.param("id", "0")
				.param("firstName", "Zażółć")
				.param("lastName", "Gęśląjaźń")
				.param("enabled", "1"))
				.andExpect(redirectedUrl("/readers")); // sprawdzamy czy nastąpiło przekierowanie
		verify(mockReaderService,atLeastOnce()).updateReader(reader);// sprawdzenie czy została wywołana metoda
																			// updateReader z danymi testowego czytelnika
			
	}

	public List<ReadersView> fakeReadersList(int size) { // metoda tworząca listę podaną czytelników (klasy ReadersView)
		List<ReadersView> list = new ArrayList<ReadersView>();
		for (int i = 0; i < size; i++) {
			list.add(new ReadersView("Imię " + i, "Nazwisko " + i));
		}
		return list;
	}

}

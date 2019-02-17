package pl.igorr.nowabiblioteka.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
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
import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.web.ReadersController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = pl.igorr.nowabiblioteka.config.RootConfig.class)
public class ReadersControllerTest {

	@Autowired
	LibraryDAO library;

	@Test
	@Transactional
	@Rollback(true)
	public void shouldShowReaders() throws Exception {
		List<Reader> expectedReaders = library.listReaders(); // pobranie listy czytelników z bazy

		ReadersController controller = new ReadersController(library); // nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/readers.jsp"))
				.build(); // utworzenie mocka MVC

		mockMvc.perform(get("/readers")) // wywołanie widoku czytelników
				.andExpect(view().name("readers")) // sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("readerList")) // ... z własciwym modelem ...
				.andExpect(model().attribute("readerList", hasItems(expectedReaders.toArray()))); // ... i elementami
																									// (hasItemsz
																									// hamcrest-a)
	}

	@Test
	public void testAddReaderPage() throws Exception {
		ReadersController controller = new ReadersController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
		mockMvc.perform(get("/readers/add"))
				.andExpect(view().name("addReaderForm")); // sprawdzamy czy wywołanie strony
																					// dodawania czytelnika wyświetla
																					// właściwy widok
	}

	@Test
	@Transactional
	@Rollback(true)
	public void shouldAddNewReader() throws Exception {

		ReadersController controller = new ReadersController(library); // nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.build(); // utworzenie mocka MVC
		
		mockMvc.perform(post("/readers/add") // Wywołujemy żądanie
				.param("firstName", "Zażółć")
				.param("lastName", "Gęśłą Jaźń"))
				.andExpect(redirectedUrl("/readers"));
		
		Reader reader = library.showLastReader();
		String lastReadersName = reader.getFirstName()+reader.getLastName(); //pobranie ostatnio dodanego czytelnika
		System.out.println(lastReadersName);
		assertEquals("ZażółćGęśłą Jaźń", lastReadersName); //sprawdzenie, czy ostatnio dodany czytelnik jest zgodny z wartościami z zapytania
			
	}

}

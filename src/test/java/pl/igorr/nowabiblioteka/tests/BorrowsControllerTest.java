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

import pl.igorr.nowabiblioteka.api.BorrowService;
import pl.igorr.nowabiblioteka.db.LibraryDAOOld;
import pl.igorr.nowabiblioteka.domain.Borrow;
import pl.igorr.nowabiblioteka.domain.BorrowsView;
import pl.igorr.nowabiblioteka.web.BorrowsController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=pl.igorr.nowabiblioteka.config.RootConfig.class)
public class BorrowsControllerTest {
	
	@Autowired
	BorrowService borrowService;

	@Test
	@Transactional
	@Rollback(true)
	public void shouldShowBorrowings() throws Exception {
		List<BorrowsView> expectedBorrowings = borrowService.listBorrows(); //pobranie listy wypożyczeń z bazy

		BorrowsController controller = new BorrowsController(borrowService, null, null); //nowy kontroler dla MockMVC

		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/borrowings.jsp"))
				.build(); //utworzenie mocka MVC
		
		mockMvc.perform(get("/borrows")) // wywołanie widoku czytelników
				.andExpect(view().name("borrows")) //sprawdzenie czy został wyświetlony właściwy widok...
				.andExpect(model().attributeExists("borrowList")) // ... z własciwym modelem ...
				.andExpect(model().attribute("borrowList", hasItems(expectedBorrowings.toArray()))); // ... i elementami (hasItemsz hamcrest-a)
	}
	

}

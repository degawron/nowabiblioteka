package pl.igorr.nowabiblioteka.tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.igorr.nowabiblioteka.db.LibraryDAO;

public class HibernateMockTest {

	@Mock
	LibraryDAO mockLibrary;
	
	@Before
	public void initMocks() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void canMockDAO() {
		
	}
	
	
}

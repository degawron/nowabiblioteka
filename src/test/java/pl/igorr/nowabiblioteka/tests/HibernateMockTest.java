package pl.igorr.nowabiblioteka.tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.igorr.nowabiblioteka.db.LibraryDAOOld;

public class HibernateMockTest {

	@Mock
	LibraryDAOOld mockLibrary;
	
	@Before
	public void initMocks() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void canMockDAO() {
		
	}
	
	
}

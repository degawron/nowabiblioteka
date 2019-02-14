package pl.igorr.nowabiblioteka.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.igorr.nowabiblioteka.domain.Books;
import pl.igorr.nowabiblioteka.domain.Borrowings;
import pl.igorr.nowabiblioteka.domain.Readers;

@Component
@Transactional
public class LibraryDAO {

	private SessionFactory sessionFactory; //przekazanie beana z PersistenceConfig
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Readers> listReaders() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Readers> readers = session.createQuery("from Readers").getResultList(); //wygenerowanie listy czytelników
		return readers;
	}
	
	@SuppressWarnings("unchecked")
	public List<Books> listBooks() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Books> books = session.createQuery("from Books").getResultList(); //wygenerowanie listy ksiazek
		return books;
	}
	
	@SuppressWarnings("unchecked")
	public List<Borrowings> listBorrowings() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Borrowings> borrowings = session.createQuery("from Borrowings").getResultList(); //wygenerowanie listy wypozyczeń
		return borrowings;
	}

}

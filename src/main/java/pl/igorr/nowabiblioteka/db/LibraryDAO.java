package pl.igorr.nowabiblioteka.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.igorr.nowabiblioteka.domain.Book;
import pl.igorr.nowabiblioteka.domain.Borrowing;
import pl.igorr.nowabiblioteka.domain.Reader;

@Component
@Transactional
public class LibraryDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {  //przekazanie beana z PersistenceConfig
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reader> listReaders() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Reader> readers = session.createQuery("from Reader").getResultList(); //wygenerowanie listy czytelników
		return readers;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> listBooks() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Book> books = session.createQuery("from Book").getResultList(); //wygenerowanie listy ksiazek
		return books;
	}
	
	@SuppressWarnings("unchecked")
	public List<Borrowing> listBorrowings() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Borrowing> borrowings = session.createQuery("from Borrowing").getResultList(); //wygenerowanie listy wypozyczeń
		return borrowings;
	}
	
	public int addReader(Reader reader) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		session.save(reader);
		return reader.getId();
	}
	
	public Reader showReader (int id) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		Reader reader = session.get(Reader.class, id);
		return reader;
	}
	
	@SuppressWarnings("unchecked")
	public Reader showLastReader () {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Integer> id = session.createQuery("select max(id) from Reader").getResultList();
		Reader reader = showReader(id.get(0));
		return reader;
	}
}

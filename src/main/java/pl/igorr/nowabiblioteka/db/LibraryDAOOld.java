package pl.igorr.nowabiblioteka.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.igorr.nowabiblioteka.domain.Book;
import pl.igorr.nowabiblioteka.domain.Borrow;
import pl.igorr.nowabiblioteka.domain.BorrowsView;
import pl.igorr.nowabiblioteka.domain.Reader;
import pl.igorr.nowabiblioteka.domain.ReadersView;

@Component
@Transactional
public class LibraryDAOOld {

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
	public List<Reader> listActiveReaders() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Reader> readers = session.createQuery("from Reader where active=1").getResultList(); //wygenerowanie listy aktywnych czytelników
		return readers;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReadersView> getReadersView() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<ReadersView> readers = session.createQuery("from ReadersView").getResultList(); //wygenerowanie listy czytelników
		return readers;
	}
	
	public int addReader(Reader reader) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		session.save(reader);
		return reader.getId();
	}
	
	public Reader getReader (int id) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		Reader reader = session.get(Reader.class, id);
		return reader;
	}
	
	public int updateReader(Reader reader) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		session.update(reader); //zapisanie poprawionych danych
		return reader.getId();
	}
	
	@SuppressWarnings("unchecked")
	public Reader getLastReader () {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Integer> id = session.createQuery("select max(id) from Reader").getResultList(); //wyszukanie najwyższego ID
		Reader reader = getReader(id.get(0)); //wyszukanie rekordu z najwyższym ID
		return reader;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> listBooks() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Book> books = session.createQuery("from Book").getResultList(); //wygenerowanie listy ksiazek
		return books;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> listAvailableBooks() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Book> books = session.createQuery("from Book where quantity>0").getResultList(); //wygenerowanie listy dostępnych ksiazek
		return books;
	}
	
	public int addBook(Book book) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		session.save(book); //zapisanie nowej książki
		return book.getId();
	}
	
	public Book getBook(int id) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		Book book = session.get(Book.class, id); //wyszukanie ksiazki po ID
		return book;
	}
	
	public int updateBook(Book book) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		session.update(book); //zapisanie zmienionej książki
		return book.getId();
	}
	
	@SuppressWarnings("unchecked")
	public List<Borrow> listBorrows() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<Borrow> borrowings = session.createQuery("from Borrow").getResultList(); //wygenerowanie listy wypozyczeń
		return borrowings;
	}
	
	@SuppressWarnings("unchecked")
	public List<BorrowsView> getBorrowsView() {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		List<BorrowsView> borrows = session.createQuery("from BorrowsView").getResultList(); //wygenerowanie listy czytelników
		return borrows;
	}

	public int newBorrow(Borrow borrow) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		Book book = session.get(Book.class, borrow.getBook().getId()); //wyszukanie wypożyczanej ksiązki
		book.setQuantity(book.getQuantity()-1); //zmienjszenie ilości dostępnych książek o 1
		session.update(book); //zapisanie nowej ilości książki
		session.save(borrow); //zapisanie nowego wypoyczenia
		return borrow.getId();
	}
	
	public Borrow getBorrow(int id) {
		Session session = sessionFactory.getCurrentSession(); // sesja ze springa
		Borrow borrow = session.get(Borrow.class, id);//wyszukanie wypozyczenia
		return borrow;
	}
	
	public int returnBook (Borrow borrow) {
		Session session = sessionFactory.getCurrentSession(); //sesja ze Springa
		Borrow savedBorrow = session.get(Borrow.class, borrow.getId()); //wyszukanei rekordy wypozyczneia zwracanej ksiązki
		savedBorrow.setDateOfReturn(borrow.getDateOfReturn()); //przepisanie daty zwrotu z danych przekazanych z formularza
		session.update(savedBorrow); //zapisanie zmiany w wypożyczeniu
		Book book = session.get(Book.class, borrow.getBook().getId()); //znalezienie ksiązki, która została zwrócona
		book.setQuantity(book.getQuantity()+1); //zwiększenie ilości dostepnych książek
		session.update(book); //zapis zmienionej książki
		return borrow.getId();
	}
	
}

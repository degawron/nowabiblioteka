package pl.igorr.nowabiblioteka.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.igorr.nowabiblioteka.api.BookService;
import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.Book;

@Service
public class BookServiceImpl implements BookService {

	private LibraryDAO libraryDAO;
	
	@Autowired
	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBooks() {
		List<Book> list = (List<Book>) libraryDAO.list("from Book");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listAvailableBooks() {
		List<Book> list = (List<Book>) libraryDAO.list("from Book where quantity>0");
		return list;
	}

	@Override
	public int addBook(Book book) {
		libraryDAO.add(book);
		return book.getId();
	}

	@Override
	public Book getBook(int id) {
		Book book = (Book) libraryDAO.get(Book.class, id);
		return book;
	}

	@Override
	public int updateBook(Book book) {
		libraryDAO.update(book);
		return book.getId();
	}

}

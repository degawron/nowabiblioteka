package pl.igorr.nowabiblioteka.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.igorr.nowabiblioteka.domain.Book;

@Repository
public interface BookService {

	public List<Book> listBooks();
	public List<Book> listAvailableBooks();
	public int addBook(Book book);
	public Book getBook(int id);
	public int updateBook(Book book);
	
}

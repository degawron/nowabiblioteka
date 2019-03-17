package pl.igorr.nowabiblioteka.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.igorr.nowabiblioteka.api.BorrowService;
import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.Book;
import pl.igorr.nowabiblioteka.domain.Borrow;
import pl.igorr.nowabiblioteka.domain.BorrowsView;

@Service
public class BorrowServiceImpl implements BorrowService {

	private LibraryDAO libraryDAO;
	
	@Autowired
	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BorrowsView> listBorrows() {
		List<BorrowsView> list = (List<BorrowsView>) libraryDAO.list("from BorrowsView");
		return list;
	}

	public Borrow getBorrow(int id) {
		Borrow borrow = (Borrow) libraryDAO.get(Borrow.class, id);
		return borrow;
	}

	@Override
	public int borrowBook(Borrow borrow) {
		Book book = (Book) libraryDAO.get(Book.class, borrow.getBook().getId());
		book.setQuantity(book.getQuantity()-1);
		libraryDAO.update(book);
		libraryDAO.add(borrow);
		return borrow.getId();
	}

	@Override
	public void returnBook(Borrow borrow) {
		Book book = (Book) libraryDAO.get(Book.class, borrow.getBook().getId());
		book.setQuantity(book.getQuantity()+1);
		libraryDAO.update(book);
		Borrow savedBorrow = getBorrow(borrow.getId());
		savedBorrow.setDateOfReturn(borrow.getDateOfReturn());
		libraryDAO.update(savedBorrow);

	}

}

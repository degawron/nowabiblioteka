package pl.igorr.nowabiblioteka.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.igorr.nowabiblioteka.domain.Borrow;
import pl.igorr.nowabiblioteka.domain.BorrowsView;

@Repository
public interface BorrowService {

	public List<BorrowsView> listBorrows();
	public Borrow getBorrow(int id);
	public int borrowBook(Borrow borrow);
	public void returnBook(Borrow borrow);
	
}

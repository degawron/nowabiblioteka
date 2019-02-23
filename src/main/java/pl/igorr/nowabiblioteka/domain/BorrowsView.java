package pl.igorr.nowabiblioteka.domain;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="borrows_view")
public class BorrowsView {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="borrow_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="book")
	private Book book;

	@ManyToOne
	@JoinColumn(name="reader")
	private Reader reader;
	
	@Column(name="date_of_borrow")
	private Date dateOfBorrow;
	
	@Column(name="term")
	private int term;

	@Column(name="date_of_return")
	private Date dateOfReturn;
	
	@Column(name="borrow_time")
	private int borrowTime;
	
	public BorrowsView() {
		
	}

	public BorrowsView(Book book, Reader reader, Date dateOfBorrow, int term, Date dateOfReturn) {
		super();
		this.book = book;
		this.reader = reader;
		this.dateOfBorrow = dateOfBorrow;
		this.term = term;
		this.dateOfReturn = dateOfReturn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Date getDateOfBorrow() {
		return dateOfBorrow;
	}

	public void setDateOfBorrow(Date dateOfBorrow) {
		this.dateOfBorrow = dateOfBorrow;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public int getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(int borrowTime) {
		this.borrowTime = borrowTime;
	}

	@Override
	public String toString() {
		return "Wypozyczenia [id=" + id + ", ksiazka=" + book
				+ ", czytelnik=" + reader + ", wypozyczenie="
				+ dateOfBorrow + ", termin=" + term + ", zwrot="
				+ dateOfReturn + ", czas od wypożyczenia=" + borrowTime + "]";
	}

	
}

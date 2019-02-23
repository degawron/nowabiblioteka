package pl.igorr.nowabiblioteka.domain;

import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="borrows")
public class Borrow {
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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") //ustala format zgodny z MySQL i pozwala na zapis przez hiberante bez konwersji formatu z formualrza
	@Column(name="date_of_borrow")
	private Date dateOfBorrow;
	
	@Column(name="term")
	private int term;

	@DateTimeFormat(pattern = "yyyy-MM-dd") //ustala format zgodny z MySQL i pozwala na zapis przez hiberante bez konwersji formatu z formualrza
	@Column(name="date_of_return")
	private Date dateOfReturn;
	
	public Borrow() {
		
	}

	public Borrow(Book book, Reader reader, Date dateOfBorrow, int term, Date dateOfReturn) {
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

	@Override
	public String toString() {
		return "Wypozyczenia [id=" + id + ", ksiazka=" + book
				+ ", czytelnik=" + reader + ", wypozyczenie="
				+ dateOfBorrow + ", termin=" + term + ", zwrot="
				+ dateOfReturn + "]";
	}

	
}

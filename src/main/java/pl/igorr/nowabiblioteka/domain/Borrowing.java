package pl.igorr.nowabiblioteka.domain;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="wypozyczenia")
public class Borrowing {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_wypozyczenia")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ksiazka")
	private Book book;

	@ManyToOne
	@JoinColumn(name="czytelnik")
	private Reader reader;
	
	@Column(name="data_wypozyczenia")
	private Date dateOfBorrow;
	
	@Column(name="termin")
	private int term;

	@Column(name="data_zwrotu")
	private Date dateOfReturn;
	
	public Borrowing() {
		
	}

	public Borrowing(Book book, Reader reader, Date dateOfBorrow, int term, Date dateOfReturn) {
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

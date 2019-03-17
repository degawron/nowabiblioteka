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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((dateOfBorrow == null) ? 0 : dateOfBorrow.hashCode());
		result = prime * result + ((dateOfReturn == null) ? 0 : dateOfReturn.hashCode());
		result = prime * result + id;
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		result = prime * result + term;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Borrow other = (Borrow) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (dateOfBorrow == null) {
			if (other.dateOfBorrow != null)
				return false;
		} else if (!dateOfBorrow.equals(other.dateOfBorrow))
			return false;
		if (dateOfReturn == null) {
			if (other.dateOfReturn != null)
				return false;
		} else if (!dateOfReturn.equals(other.dateOfReturn))
			return false;
		if (id != other.id)
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		if (term != other.term)
			return false;
		return true;
	}

	
}

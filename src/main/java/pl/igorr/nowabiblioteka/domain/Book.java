package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private int id;
	
	@NotEmpty(message= "{book.title.notempty}")
	@Column(name="title")
	private String title;

	@NotEmpty(message= "{book.author.notempty}")
	@Column(name="author")
	private String author;

	@Column(name="year")
	private int year;
	
	@Min(value=0, message= "{book.quantity.negative}")
	@Column(name="quantity")
	private int quantity;
	
	public Book(){
		
	}

	public Book(String title, String author, int year, int quantity) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Ksiazki [id=" + id + ", tytul=" + title + ", autor=" + author
				+ ", rok=" + year + ", ilosc=" + quantity + "]";
	}

	
}

package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private int id;
	
	@NotNull
	@Column(name="title")
	private String title;

	@NotNull
	@Column(name="author")
	private String author;

	@NotNull
	@Column(name="year")
	private int year;
	
	@NotNull
	@Min(value=0)
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

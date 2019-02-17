package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;

@Entity
@Table(name="ksiazki")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ksiazki")
	private int id;
	
	@Column(name="tytul")
	private String title;

	@Column(name="autor")
	private String author;

	@Column(name="rok")
	private int year;
	
	@Column(name="ilosc")
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

package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="readers")
public class Reader {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reader_id")
	private int id;
	
	@NotEmpty
	@Column(name="first_name")
	String firstName;

	@NotEmpty
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="active")
	private int active;


	public Reader() {

	}

	public Reader(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = 1;
	}
	
	public Reader(String firstName, String lastName, int active) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Czytelnicy [id=" + id + ", imie=" + firstName + ", nazwisko=" + lastName + ", aktywny=" + active + "]";
	}
}
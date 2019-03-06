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
	
	@NotEmpty(message= "{reader.firstName.notempty}")
	@Column(name="first_name")
	String firstName;

	@NotEmpty(message= "{reader.lastName.notempty}")
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="enabled")
	private int enabled;


	public Reader() {

	}

	public Reader(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = 1;
	}
	
	public Reader(String firstName, String lastName, int enabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Czytelnik [id=" + id + ", imie=" + firstName + ", nazwisko=" + lastName + ", aktywny=" + enabled + "]";
	}
}
package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;

@Entity
@Table(name="czytelnicy")
public class Readers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_czytelnika")
	private int id;
	
	@Column(name="imie")
	private String firstName;

	@Column(name="nazwisko")
	private String lastName;
	
	@Column(name="aktywny")
	private int active;


	public Readers() {

	}

	public Readers(String firstName, String lastName, int active) {
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
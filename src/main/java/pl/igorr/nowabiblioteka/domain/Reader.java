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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + enabled;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		Reader other = (Reader) obj;
		if (enabled != other.enabled)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
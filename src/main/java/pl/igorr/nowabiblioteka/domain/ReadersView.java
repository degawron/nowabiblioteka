package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;

@Entity
@Table(name="readers_view")
public class ReadersView {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reader_id")
	private int id;
	
	@Column(name="first_name")
	String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="active")
	private int active;
	
	@Column(name="borrows")
	private long borrows;
	
	@Column(name="not_returned")
	private long notReturned;
	

	public ReadersView() {

	}

	public ReadersView(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = 1;
	}
	
	public ReadersView(String firstName, String lastName, int active) {
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

	public long getBorrows() {
		return borrows;
	}

	public void setBorrows(long borrows) {
		this.borrows = borrows;
	}

	public long getNotReturned() {
		return notReturned;
	}

	public void setNotReturned(long notReturned) {
		this.notReturned = notReturned;
	}

	@Override
	public String toString() {
		return "Czytelnicy [id=" + id + ", imie=" + firstName + ", nazwisko=" + lastName + ", aktywny=" + active + ", wypożyczenia=" + borrows + ", niezrócone=" + notReturned + "]";
	}
}
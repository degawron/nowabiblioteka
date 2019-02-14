package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;

@Entity
@Table(name="czasy_wypozyczen")
public class CzasyWypozyczen {
	
	@Id
	@Column(name="id_wypozyczenia")
	private int idWypożyczenia;
	
	@Column(name="czas_wypozyczenia")
	private int czasWypozyczenia;

	public CzasyWypozyczen() {
	}

	public CzasyWypozyczen(int idWypożyczenia, int czasWypozyczenia) {
		this.idWypożyczenia = idWypożyczenia;
		this.czasWypozyczenia = czasWypozyczenia;
	}

	public int getIdWypożyczenia() {
		return idWypożyczenia;
	}

	public void setIdWypożyczenia(int idWypożyczenia) {
		this.idWypożyczenia = idWypożyczenia;
	}

	public int getCzasWypozyczenia() {
		return czasWypozyczenia;
	}

	public void setCzasWypozyczenia(int czasWypozyczenia) {
		this.czasWypozyczenia = czasWypozyczenia;
	}

}

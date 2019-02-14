package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;

@Entity
@Table(name="ilosc_wypozyczen")
public class IloscWypozyczen {
	
	@Id
	@Column(name="czytelnik")
	private int czytelnik;

	@Column(name="ilosc")
	private int ilosc;
	
	@Column(name="ilosc_niezwroconych")
	private int iloscNiezroconych;
	
	
	public IloscWypozyczen() {
		
	}

	public IloscWypozyczen(int czytelnik, int ilosc, int iloscNiezroconych) {
		this.czytelnik = czytelnik;
		this.ilosc = ilosc;
		this.iloscNiezroconych = iloscNiezroconych;
	}

	public int getCzytelnik() {
		return czytelnik;
	}

	public void setCzytelnik(int czytelnik) {
		this.czytelnik = czytelnik;
	}

	public int getIlosc() {
		return ilosc;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public int getIloscNiezroconych() {
		return iloscNiezroconych;
	}

	public void setIloscNiezroconych(int iloscNiezroconych) {
		this.iloscNiezroconych = iloscNiezroconych;
	}

}

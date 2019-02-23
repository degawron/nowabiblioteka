package pl.igorr.nowabiblioteka.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.Reader;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping(value="/readers") //mapowanie dla podanej ścieżki
public class ReadersController {
	
	LibraryDAO library;
	
	public ReadersController () {
	
	}
	
	@Autowired
	public ReadersController (LibraryDAO library) { //wstrzyknięcie DAO biblioteki
		this.library = library;
	}

	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String showReaders(Model model) {
		model.addAttribute(library.getReadersView()); //wstawienie listy czytelników jako modelu
		return "readers"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String addReaderForm(Model model) {
		model.addAttribute(new Reader());
		return "readerForm"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.POST) //obsługa żadania POST w poniższej metodzie
	public String addReader(@Valid Reader reader, Errors errors) {
		reader.setActive(1); // ustawienie czytelnika jako aktywnego
		if (errors.hasErrors()) return "readerForm"; //Walidacja przekazywanych pól -> w razie błędu powrót do formularza
		library.addReader(reader); //dodanie nowego czytelnika do bazy
		return "redirect:/readers"; //zwrócenie przekierowania do widoku (zamiast widoku bezpośrednio)
	}
	
	@RequestMapping (value="/edit/{readerId}", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie z parametrem w adresie
	public String editReaderForm(@PathVariable("readerId") int readerId, Model model){
		model.addAttribute(library.getReader(readerId)); //ustawienie pobranego czytelnika jako modelu dla widoku
		return "readerForm"; //zwrócenie widoku (przekazanie danych edytowanego czytelnika jako modelu) 
	}
	
	@RequestMapping (value="/edit/{readerId}", method = RequestMethod.POST) //obsługa żadania POST w poniższej metodzie z parametrem w adresie (parameter nie jest używany)
	public String editReader(@Valid Reader reader, Errors errors){
		if (errors.hasErrors()) return "readerForm"; //sprawdzenie poprawności formularza
		library.updateReader(reader); //zapis zmienionych danych
		return "redirect:/readers"; //zwrócenie przekierowania do widoku (zamiast widoku bezpośrednio)
	}
	
}

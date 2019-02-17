package pl.igorr.nowabiblioteka.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
		model.addAttribute(library.listReaders()); //wstawienie listy czytelników jako modelu
		return "readers"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String showAddReaderForm() {
		return "addReaderForm"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.POST) //obsługa żadania POST w poniższej metodzie
	public String addReader(@Valid Reader reader, Errors errors) {
		reader.setActive(1);
		if (errors.hasErrors()) return "addReaderForm"; //Walidacja przekazywanych pól -> w razie błędu powrót do formularza
		library.addReader(reader);
		return "redirect:/readers"; //zwrócenie przekierowania do widoku (zamiast widoku bezpośrednio)
	}
	
	@RequestMapping (value="/edit", method = RequestMethod.POST) //obsługa żadania GET w poniższej metodzie
	public String editReader(Reader reader) {
		return "editReaderForm"; //zwrócenie widoku (przekazanie danych edytowanego czytelnika jako modelu) 
	}
	
}

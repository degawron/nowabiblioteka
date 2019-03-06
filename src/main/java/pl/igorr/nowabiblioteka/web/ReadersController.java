package pl.igorr.nowabiblioteka.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.igorr.nowabiblioteka.api.ReaderService;
import pl.igorr.nowabiblioteka.domain.Reader;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping(value="/readers") //mapowanie dla podanej ścieżki
public class ReadersController {
	
	private ReaderService readerService;
	
	public ReadersController () {
	
	}
	
	@Autowired
	public ReadersController (ReaderService readerService) { //wstrzyknięcie usługi obsługującej operacje na czytelnikach
		this.readerService = readerService;
	}
	
	
	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String showReaders(Model model) {
		model.addAttribute(readerService.listReaders()); //wstawienie listy czytelników jako modelu
		return "readers"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String addReaderForm(Model model) {
		model.addAttribute(new Reader());
		return "readerForm"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.POST) //obsługa żadania POST w poniższej metodzie
	public String addReader(@Valid Reader reader, Errors errors) {
		reader.setEnabled(1); // ustawienie czytelnika jako aktywnego
		if (errors.hasErrors()) return "readerForm"; //Walidacja przekazywanych pól -> w razie błędu powrót do formularza
		readerService.addReader(reader); //dodanie nowego czytelnika do bazy
		return "redirect:/readers"; //zwrócenie przekierowania do widoku (zamiast widoku bezpośrednio)
	}
	
	@RequestMapping (value="/edit/{readerId}", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie z parametrem w adresie
	public String editReaderForm(@PathVariable("readerId") int readerId, Model model){
		model.addAttribute(readerService.getReader(readerId)); //ustawienie pobranego czytelnika jako modelu dla widoku
		return "readerForm"; //zwrócenie widoku (przekazanie danych edytowanego czytelnika jako modelu) 
	}
	
	@RequestMapping (value="/edit/{readerId}", method = RequestMethod.POST) //obsługa żadania POST w poniższej metodzie z parametrem w adresie (parameter nie jest używany)
	public String editReader(@Valid Reader reader, Errors errors){
		if (errors.hasErrors()) return "readerForm"; //sprawdzenie poprawności formularza
		readerService.updateReader(reader); //zapis zmienionych danych
		return "redirect:/readers"; //zwrócenie przekierowania do widoku (zamiast widoku bezpośrednio)
	}
	
}

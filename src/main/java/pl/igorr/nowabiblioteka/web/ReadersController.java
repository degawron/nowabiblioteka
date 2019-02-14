package pl.igorr.nowabiblioteka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.igorr.nowabiblioteka.db.LibraryDAO;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping({"/readers"}) //mapowanie dla podanej ścieżki
public class ReadersController {
	
	LibraryDAO library;
	
	@Autowired
	public ReadersController (LibraryDAO library) {
		this.library = library;
	}

	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String showReaders(Model model) {
		model.addAttribute(library.listReaders());
		return "readers"; //zwracamy nazwę widoku
	}
	

}

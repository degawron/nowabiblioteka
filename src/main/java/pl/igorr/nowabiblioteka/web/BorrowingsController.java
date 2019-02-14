package pl.igorr.nowabiblioteka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.igorr.nowabiblioteka.db.LibraryDAO;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping({"/borrowings"}) //mapowanie dla podanej ścieżki
public class BorrowingsController {
	
	LibraryDAO library;

	@Autowired
	public BorrowingsController (LibraryDAO library) {
		this.library = library;
	}
	
	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String czytelnicy(Model model) {
		model.addAttribute(library.listBorrowings());
		return "borrowings"; //zwracamy nazwę widoku
	}
}

package pl.igorr.nowabiblioteka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.igorr.nowabiblioteka.db.LibraryDAO;
import pl.igorr.nowabiblioteka.domain.Borrow;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping({"/borrows"}) //mapowanie dla podanej ścieżki
public class BorrowsController {
	
	LibraryDAO library;

	@Autowired
	public BorrowsController (LibraryDAO library) { //wstrzyknięcie DAO biblioteki
		this.library = library;
	}
	
	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String showBorrows(Model model) {
		model.addAttribute(library.getBorrowsView()); //wstawienie listy wypożyczeń jako modelu
		return "borrows"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/new", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String newBorrowForm(Model model) {
		model.addAttribute(new Borrow()); //dodanie nowego wypożyczenia do modelu
		model.addAttribute(library.listAvailableBooks()); //wstawienie listy dostępnych książek do modelu
		model.addAttribute(library.listActiveReaders()); //wstawienie listy czytelników do modelu
		return "borrowForm"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/new", method = RequestMethod.POST) //obsługa żadania GET w poniższej metodzie
	public String newBorrow(Borrow borrow) {
		System.out.println(borrow);
		library.newBorrow(borrow);
		return "redirect:/borrows"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/return/{borrowId}", method = RequestMethod.GET) //obsługa żadania GET 
	public String returnBookForm(@PathVariable("borrowId") int borrowId, Model model) {
		model.addAttribute(library.getBorrow(borrowId)); //dodanie do modelu wypozyczenia o ID przekazanym w ścieżce
		return "returnBookForm";
	}
	
	@RequestMapping (value="/return/{borrowId}", method = RequestMethod.POST) //obsługa żądania POST (parametr w ścieżce jest nieużywany)
	public String returnBook (Borrow borrow) {
		library.returnBook(borrow);
		return "redirect:/borrows";
	}
}

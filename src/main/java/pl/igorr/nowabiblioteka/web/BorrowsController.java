package pl.igorr.nowabiblioteka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.igorr.nowabiblioteka.api.BookService;
import pl.igorr.nowabiblioteka.api.BorrowService;
import pl.igorr.nowabiblioteka.api.ReaderService;
import pl.igorr.nowabiblioteka.domain.Borrow;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping({"/borrows"}) //mapowanie dla podanej ścieżki
public class BorrowsController {
	
	ReaderService readerService;
	BookService bookService;
	BorrowService borrowService;

	@Autowired
	public BorrowsController (BorrowService borrowService, ReaderService readerService, BookService bookService) { //wstrzyknięcie DAO biblioteki
		this.borrowService = borrowService;
		this.readerService = readerService;
		this.bookService = bookService;
	}
	
	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String showBorrows(Model model) {
		model.addAttribute(borrowService.listBorrows()); //wstawienie listy wypożyczeń jako modelu
		return "borrows"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/new", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String newBorrowForm(Model model) {
		
		model.addAttribute(new Borrow()); //dodanie nowego wypożyczenia do modelu
		model.addAttribute(bookService.listAvailableBooks()); //wstawienie listy dostępnych książek do modelu
		model.addAttribute(readerService.listActiveReaders()); //wstawienie listy aktywnych czytelników do modelu
		return "borrowForm"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/new", method = RequestMethod.POST) //obsługa żadania GET w poniższej metodzie
	public String newBorrow(Borrow borrow) {
		System.out.println(borrow);
		borrowService.borrowBook(borrow);
		return "redirect:/borrows"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/return/{borrowId}", method = RequestMethod.GET) //obsługa żadania GET 
	public String returnBookForm(@PathVariable("borrowId") int borrowId, Model model) {
		model.addAttribute(borrowService.getBorrow(borrowId)); //dodanie do modelu wypozyczenia o ID przekazanym w ścieżce
		return "returnBookForm";
	}
	
	@RequestMapping (value="/return/{borrowId}", method = RequestMethod.POST) //obsługa żądania POST (parametr w ścieżce jest nieużywany)
	public String returnBook (Borrow borrow) {
		borrowService.returnBook(borrow);
		return "redirect:/borrows";
	}
}

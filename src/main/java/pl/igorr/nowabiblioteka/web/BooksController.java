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
import pl.igorr.nowabiblioteka.domain.Book;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping({"/books"}) //mapowanie dla podanej ścieżki
public class BooksController {
	
	LibraryDAO library;

	@Autowired
	public BooksController (LibraryDAO library) { //wstrzyknięcie DAO biblioteki
		this.library = library;
	}
	
	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String showBooks(Model model) {
		model.addAttribute(library.listBooks()); //wstawienie listy książek jako modelu
		return "books"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String addBookForm(Model model) {
		model.addAttribute(new Book());
		return "bookForm"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/add", method = RequestMethod.POST) //obsługa żadania POST w poniższej metodzie
	public String addBook(@Valid Book book, Errors errors) {
		if (errors.hasErrors()) return "bookForm"; //Walidacja przekazywanych pól -> w razie błędu powrót do formularza
		library.addBook(book); //dodanie nowej ksiązki do bazy
		return "redirect:/books"; //zwrócenie przekierowania do widoku (zamiast widoku bezpośrednio)
	}
	
	@RequestMapping (value="/edit/{bookId}", method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String editBookForm(@PathVariable("bookId") int bookId, Model model) {
		model.addAttribute(library.getBook(bookId)); //przekazanie do modelu wyszukanej po ID książki
		return "bookForm"; //zwrócenie nazwy widoku
	}
	
	@RequestMapping (value="/edit/{bookId}", method = RequestMethod.POST) //obsługa żadania POST w poniższej metodzie
	public String editBook(@Valid Book book, Errors errors) {
		if (errors.hasErrors()) return "bookForm"; //Walidacja przekazywanych pól -> w razie błędu powrót do formularza
		library.updateBook(book); //dodanie nowej ksiązki do bazy
		return "redirect:/books"; //zwrócenie przekierowania do widoku (zamiast widoku bezpośrednio)
	}
}

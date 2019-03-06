package pl.igorr.nowabiblioteka.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.igorr.nowabiblioteka.api.UserService;
import pl.igorr.nowabiblioteka.domain.UsersView;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping(value = "/users") // mapowanie dla podanej ścieżki
public class UsersController {

	private UserService userService;

	public UsersController() {

	}

	@Autowired
	public UsersController(UserService readerService) { // wstrzyknięcie usługi obsługującej operacje na czytelnikach
		this.userService = readerService;
	}

	@RequestMapping(method = RequestMethod.GET) // obsługa żądania GET w poniższej metodzie
	public String showUsers(Model model) {
		model.addAttribute(userService.listUsers()); // wstawienie listy czytelników jako modelu
		return "users"; // zwrócenie nazwy widoku
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET) // obsługa żądania GET w poniższej metodzie
	public String addUserForm(Model model) {
		model.addAttribute(new UsersView()); // dodanie nowego użytkownika(+uprawnienia) jako modelu dla formularza
		return "userForm";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST) // Obsługa formularza wysłanego metodą POST
	public String addUser(@Valid UsersView user, Errors errors) {
		if (errors.hasErrors()) return "userForm";
		userService.createUserJDBC(user); // utworzenie nowego użytkownika
		return "redirect:/users";
	}

	//TODO obsługa edycji i usuwania użytkowników
	
}

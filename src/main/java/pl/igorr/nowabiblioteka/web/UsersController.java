package pl.igorr.nowabiblioteka.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.igorr.nowabiblioteka.api.UserService;
import pl.igorr.nowabiblioteka.domain.UserDTO;
import pl.igorr.nowabiblioteka.domain.UserDTO.AddUser;

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

	@GetMapping // obsługa żądania GET w poniższej metodzie
	public String showUsers(Model model) {
		model.addAttribute(userService.listUsers()); // wstawienie listy czytelników jako modelu
		return "users"; // zwrócenie nazwy widoku użytkowników
	}

	@GetMapping("/add") // obsługa żądania GET w poniższej metodzie
	public String addUserForm(Model model) {
		model.addAttribute(new UserDTO()); // dodanie nowego użytkownika przejściowego jako modelu dla formularza
		return "userForm"; //zwrócenie widoku - formularz użytkownika
	}

	@PostMapping("/add") // Obsługa formularza wysłanego metodą POST
	public String addUser(@Validated({AddUser.class}) UserDTO user, Errors errors) { //dodatkowa walidacja 
		if (errors.hasErrors()) return "userForm"; //powrót do formularza w razie błędu
		userService.createUser(user); // utworzenie nowego użytkownika
		return "redirect:/users"; //przekierowanie na widok użytkowników (przekierowanie w celu uniknięcia przypadkowego odświeżenia)
	}

	@GetMapping("/edit/{username}") //obsługa żądania GET w poniższej metodzie
	public String editUserForm(@PathVariable("username") String username, Model model) {
		model.addAttribute(userService.getUser(username)); //dodanie do modelu danych użytkownika pobranych z bazy po nazwie
		return "userForm";
	}
	
	@PostMapping("/edit/{username}") //obsługa żadania POST z formularza (wartość zmiennej nie jest używana)
	public String editUser(@PathVariable("username") String username, @Valid UserDTO user, Errors errors) {
		if (errors.hasErrors()) return "userForm"; //powrót do formularza w razie błędu
		userService.updateUser(user); //aktualizacja użytkownika w bazie
		return "redirect:/users"; //przekierowanie na widok użytkowników (przekierowanie w celu uniknięcia przypadkowego odświeżenia)
	}
		
}

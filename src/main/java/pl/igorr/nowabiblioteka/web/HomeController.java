package pl.igorr.nowabiblioteka.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller // Deklaracja klasy jako kontrolera
@RequestMapping({"/","/start"}) //mapowanie dla podanej ścieżki
public class HomeController {

	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String home() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		return "home"; //zwracamy nazwę widoku
	}

}

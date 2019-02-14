package pl.igorr.nowabiblioteka.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // Deklaracja klasy jako kontrolera
@RequestMapping({"/","/start"}) //mapowanie dla podanej ścieżki
public class HomeController {

	@RequestMapping (method = RequestMethod.GET) //obsługa żadania GET w poniższej metodzie
	public String home() {
		return "home"; //zwracamy nazwę widoku
	}
}

package pl.igorr.nowabiblioteka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc  //włączenie Spring MVC
@ComponentScan("pl.igorr.nowabiblioteka.web") //Włączenie skanowania komponentów
public class WebConfig implements WebMvcConfigurer { //Zamiast rozszerzania klasy WebMvcConfigurerAdapter impementujemy WebMvcConfigurer (nowe podejście w Spring 5)
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();  //Konfiguracja producenta widoków
		resolver.setPrefix("/WEB-INF/views/"); //ustawienie prefiksu...
		resolver.setSuffix(".jsp"); //... i sufixu dla widoków (do nazwy widoku zostaną dodane automatycznie
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { //Konfiguracja obsługi zasobów statycznych
		configurer.enable();
	}

}

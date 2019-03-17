package pl.igorr.nowabiblioteka.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // włączenie zabezpieczeń
public class SecurityConfig extends WebSecurityConfigurerAdapter { // konfiguracja zabezpieczeń aplikacji web

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()//włączenie autoryzacji zapytań
			.regexMatchers("/","/start").permitAll() //wykluczenie autoryzacji dla strony domowej
			.regexMatchers("/resources/style.css").permitAll() //wykluczenie autoryzacji dla arkusza stylów
			.regexMatchers("/readers/.*","/books/.*","/borrows/.*").hasAuthority("ROLE_USER") //ustawienie widoków dla których wymagane są uprawnienia użytkownika
			.regexMatchers("/users.*").hasAuthority("ROLE_ADMIN") //ustawienie widoków dla których wymagane są uprawnienia administratora
			.anyRequest().authenticated() //pozostałe adresy wymagają autoryzacji
			.and().formLogin() //włączenie springowej strony logowania
			.and().logout().logoutSuccessUrl("/") //po wylogowaniu przekierowanie na stronę startową
			.and().exceptionHandling().accessDeniedPage("/errorpages/accessDenied.jsp") //ustawienie strony wyświetlanej dla 403 braku dostępu
			.and().httpBasic(); //włączenie podstawowej autoryzacji HTTP
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.jdbcAuthentication()
        	.dataSource(dataSource);
        /* tymczasowe utworzenie użytkowników w pamięci
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); //encoder wykorzystywany do kodowania haseł przechowywanych w pamięci
			.inMemoryAuthentication()
				.withUser("user").password(encoder.encode("user")).roles("USER")
				.and()
				.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
		*/
	}
}

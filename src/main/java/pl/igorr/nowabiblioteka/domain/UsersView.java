package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import pl.igorr.nowabiblioteka.validation.UniqueUserConstraint;

@Entity
@Table(name="users_view")
public class UsersView {

	@Id
	@Column(name="username", unique=true)
	@UniqueUserConstraint //adnotacja o dodatkowo utworzonej walidacji dla formularzy
	private String username;
	
	@Column(name="password")
	@NotEmpty
	private String password;
	
	@Column(name="enabled")
	private int enabled;
	
	@Column(name="authorities")
	private String authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Użytkownik [login=" + username + ", hasło=" + password + ", aktywny=" + enabled + ", uprawnienia="
				+ authorities + "]";
	}
	
	
}

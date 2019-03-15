package pl.igorr.nowabiblioteka.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users_view")
public class UsersView {

	@Id
	@Column(name="username", unique=true)
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
	
	public UsersView() {
		super();
	}

	public UsersView(String username, String password, int enabled, String authorities) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
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

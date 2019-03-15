package pl.igorr.nowabiblioteka.domain;

import java.util.Arrays;

import javax.validation.constraints.NotEmpty;

import pl.igorr.nowabiblioteka.validation.MatchesConstraint;
import pl.igorr.nowabiblioteka.validation.UniqueUserConstraint;

@MatchesConstraint(firstField="password", secondField="confirmPassword", message="Brak zgodności hasła z potwierdzeniem")
public class UserDTO {
	
	public interface AddUser{};
	public interface EditUser{};

	@NotEmpty(message= "{user.username.notempty}", groups= {AddUser.class})
	@UniqueUserConstraint(message= "{user.username.uniqueuserconstraint}", groups= {AddUser.class}) //sprawdzanie czy użytkownik jest unikalny przez dodaną walidację
	private String username;
	
	@NotEmpty(message= "{user.password.notempty}", groups= {AddUser.class})
	private String password;
	
	@NotEmpty(message= "{user.confirmPassword.notempty}", groups= {AddUser.class})
	private String confirmPassword;
	
	private boolean enabled;

	private String[] authorities;

	public UserDTO() {
		super();
	}

	public UserDTO(@NotEmpty String username, @NotEmpty String password, @NotEmpty String confirmPassword, boolean enabled,
			String[] authorities) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.enabled = enabled;
		this.authorities = authorities;
	}
	
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", authorities=" + Arrays.toString(authorities) + "]";
	}
	  
}

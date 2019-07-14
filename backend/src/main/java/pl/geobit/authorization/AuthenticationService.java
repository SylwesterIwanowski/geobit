package pl.geobit.authorization;

import static pl.geobit.util.GeobitStringUtils.generateToken;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import pl.geobit.model.ApplicationUser;
import pl.geobit.repository.ApplicationUserRepository;

@Service
@SessionScope
public class AuthenticationService {

	private ApplicationUser user;
	private String token;

	@Autowired
	private ApplicationUserRepository userRepository;

	public ApplicationUser getCurrentUser() {
		return user;
	}

	public UserToken authenticate(String email, String password) {
		var foundUser = userRepository.findByEmail(email);
		if (foundUser == null) {
			return null;
		}
		if (foundUser.getPassword().equals(password)) {
			this.user = foundUser;
			this.token = generateToken();
			new UserToken(this.token);
		}
		return null;
	}

	public boolean isUserLoggedIn(String token) {
		return Objects.equals(this.token, token);
	}

	public ApplicationUser getLoggedInUser(String token) {
		this.isUserLoggedIn(token);
		return user;
	}
}

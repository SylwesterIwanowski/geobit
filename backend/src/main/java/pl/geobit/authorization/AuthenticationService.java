package pl.geobit.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.geobit.model.ApplicationUser;
import pl.geobit.repository.ApplicationUserRepository;

import java.util.Objects;

import static pl.geobit.util.GeobitStringUtils.generateToken;

@Service
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
			return new UserToken(this.token);
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

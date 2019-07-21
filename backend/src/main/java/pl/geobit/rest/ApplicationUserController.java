package pl.geobit.rest;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.geobit.authorization.AuthenticationService;
import pl.geobit.authorization.UserToken;
import pl.geobit.model.ApplicationUser;
import pl.geobit.model.Role;
import pl.geobit.repository.ApplicationUserRepository;

@Controller
@RequestMapping(path = "rest/user")
public class ApplicationUserController {

	@Autowired
	private ApplicationUserRepository repository;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping(path = "get")
	public @ResponseBody
	List<ApplicationUser> getAllUsers(@RequestHeader(name = "token") String token) {
		if (!authenticationService.isUserLoggedIn(token)) {
			throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
		}
		return newArrayList(repository.findAll());
	}

	@PostMapping(path = "add", consumes = "application/json")
	public @ResponseBody
	ApplicationUser addUser(@RequestBody ApplicationUser user) {
		ApplicationUser newUser = new ApplicationUser();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPhoneNumber(user.getPhoneNumber());
		newUser.setRole(Role.EMPLOYEE);
		return repository.save(newUser);
	}

	@PostMapping(path = "deleteEmployee", consumes = "application/json")
	public @ResponseBody
	void deleteUser(@RequestBody ApplicationUser user) {
		ApplicationUser foundUser = repository.findByEmail(user.getEmail());
		if (foundUser != null) {
			repository.delete(foundUser);
		}
	}

	@PostMapping(path = "login", consumes = "application/json")
	public @ResponseBody
	UserToken loginUser(@RequestBody ApplicationUser user) {
		var email = user.getEmail();
		var password = user.getPassword();
		var userToken = authenticationService.authenticate(email, password);
		if (userToken == null) {
			throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
		}
		return userToken;
	}
}

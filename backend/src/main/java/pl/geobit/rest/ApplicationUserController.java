package pl.geobit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.geobit.model.ApplicationUser;
import pl.geobit.repository.ApplicationUserRepository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Controller
@RequestMapping(path = "rest/user")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository repository;

    @GetMapping(path = "get")
    public @ResponseBody List<ApplicationUser> getAllUsers() {
        return newArrayList(repository.findAll());
    }

    @PostMapping(path = "add", consumes = "application/json")
    public @ResponseBody ApplicationUser addUser(@RequestBody ApplicationUser user) {
        ApplicationUser newUser = new ApplicationUser();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        return repository.save(newUser);
    }
}

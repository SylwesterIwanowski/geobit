package pl.geobit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping(path = "add")
    public @ResponseBody ApplicationUser addUser(@RequestParam String login) {
        ApplicationUser user = new ApplicationUser();
        user.setLogin(login);
        return repository.save(user);
    }

    @GetMapping(path = "delete", produces = "text/plain")
    public @ResponseBody String deleteUser(@RequestParam String login) {
        ApplicationUser user = repository.findByLogin(login);
        if (user == null) {
            return "User nie istnieje";
        }
        repository.delete(user);
        return "deleted";
    }
}

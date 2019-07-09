package pl.geobit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

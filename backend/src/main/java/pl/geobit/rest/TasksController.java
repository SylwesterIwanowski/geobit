package pl.geobit.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.geobit.model.Task;
import pl.geobit.repository.TaskRepository;

@Controller
@RequestMapping(path ="rest/task")
public class TasksController {

    @Autowired
    private TaskRepository repository;

    @GetMapping(path = "get")
    public @ResponseBody
    Iterable<Task> getAllTasks() {
        return repository.findAll();
    }
}

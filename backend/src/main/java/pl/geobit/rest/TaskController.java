package pl.geobit.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.geobit.model.Task;
import pl.geobit.repository.TaskRepository;

@Controller
@RequestMapping(path ="rest/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping(path = "get")
    public @ResponseBody
    Iterable<Task> getAllTasks() {
        return repository.findAll();
    }

    @PostMapping(path = "add", consumes = "application/json")
    public @ResponseBody
    Task addTask(@RequestBody Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setStartDate(task.getStartDate());
        newTask.setEndDate(task.getEndDate());
        newTask.setKerg(task.getKerg());
        newTask.setDescription(task.getDescription());
        newTask.setCustomer(task.getCustomer());
        newTask.setEmployees(task.getEmployees());

        return repository.save(newTask);
    }

}



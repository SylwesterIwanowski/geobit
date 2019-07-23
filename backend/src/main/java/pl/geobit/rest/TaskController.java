package pl.geobit.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.geobit.model.Task;
import pl.geobit.repository.TaskRepository;

import javax.validation.ValidationException;
import java.util.Optional;

@Controller
@RequestMapping(path ="rest/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping(path = "get")
    public @ResponseBody
    Iterable<Task> getAllTasks() {
        return repository.findAllByOrderByIdAsc();
    }

    @PostMapping(path = "add", consumes = "application/json")
    public @ResponseBody
    Task addTask(@RequestBody Task task) {
        if (task.getTitle() == null) {
            throw new ValidationException("Title cannot be null");
        }
        Optional<Task> foundOptionalTask;
        if (task.getId() == null) {
            foundOptionalTask = Optional.empty();
        } else {
            foundOptionalTask = repository.findById(task.getId());
        }

        if (foundOptionalTask.isEmpty()) {
            return repository.save(task);
        } else {
            Task foundTask = foundOptionalTask.get();
            foundTask.setTitle(task.getTitle());
            foundTask.setStartDate(task.getStartDate());
            foundTask.setEndDate(task.getEndDate());
            foundTask.setKerg(task.getKerg());
            foundTask.setDescription(task.getDescription());
            foundTask.setCustomer(task.getCustomer());
            foundTask.setEmployees(task.getEmployees());
            return repository.save(foundTask);
        }
    }
}



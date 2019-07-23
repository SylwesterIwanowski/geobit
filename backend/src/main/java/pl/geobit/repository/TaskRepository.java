package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByOrderByIdAsc();
}

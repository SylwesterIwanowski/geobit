package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}

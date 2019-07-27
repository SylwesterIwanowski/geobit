package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.Machine;

import java.util.List;

public interface MachineRepository extends CrudRepository<Machine, Long> {
    List<Machine> findAllByOrderByIdAsc();
}

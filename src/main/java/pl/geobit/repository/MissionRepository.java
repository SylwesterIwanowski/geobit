package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.Mission;

public interface MissionRepository extends CrudRepository<Mission, Long> {
}

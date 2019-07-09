package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.ApplicationUser;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
}

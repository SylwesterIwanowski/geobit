package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.ApplicationUser;

import java.util.List;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

	ApplicationUser findByEmail(String email);
	List<ApplicationUser> findAllByOrderByIdAsc();


}

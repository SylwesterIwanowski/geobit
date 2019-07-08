package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}

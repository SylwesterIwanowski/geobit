package pl.geobit.repository;

import org.springframework.data.repository.CrudRepository;
import pl.geobit.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAllByOrderByIdAsc();
}

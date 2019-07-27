package pl.geobit.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.geobit.model.Customer;
import pl.geobit.repository.CustomerRepository;

import javax.validation.ValidationException;
import java.util.Optional;

@Controller
@RequestMapping(path = "rest/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping(path = "get")
    public @ResponseBody
    Iterable<Customer> getAllCustomer() {
        return repository.findAllByOrderByIdAsc();
    }

    @PostMapping(path = "add", consumes = "application/json")
    public @ResponseBody
    Customer addCustomer(@RequestBody Customer customer) {
        if (customer.getName() == null) {
            throw new ValidationException("Name cannot be null");
        }
        Optional<Customer> foundOptionalCustomer;
        if (customer.getId() == null) {
            foundOptionalCustomer = Optional.empty();
        } else {
            foundOptionalCustomer = repository.findById(customer.getId());
        }

        if (foundOptionalCustomer.isEmpty()) {
            return repository.save(customer);
        } else {
            Customer foundCustomer = foundOptionalCustomer.get();
            foundCustomer.setName(customer.getName());
            foundCustomer.setPhoneNumber(customer.getPhoneNumber());
            foundCustomer.setFullName(customer.getFullName());
            foundCustomer.setNip(customer.getNip());
            foundCustomer.setDescription(customer.getDescription());
            return repository.save(foundCustomer);
        }
    }

}

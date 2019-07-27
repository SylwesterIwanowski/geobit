package pl.geobit.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.geobit.model.Machine;
import pl.geobit.repository.MachineRepository;

import javax.validation.ValidationException;
import java.util.Optional;

@Controller
@RequestMapping(path="rest/machine")
public class MachineController {

    @Autowired
    private MachineRepository repository;

    @GetMapping(path = "get")
    public @ResponseBody
    Iterable<Machine> getAllMachines() {
        return repository.findAllByOrderByIdAsc();
    }

    @PostMapping(path = "add", consumes = "application/json")
    public @ResponseBody
    Machine addMachine(@RequestBody Machine machine) {
        if (machine.getNameMachine() == null) {
            throw new ValidationException("Name cannot be null");
        }
        Optional<Machine> foundOptionalMachine;
        if (machine.getId() == null) {
            foundOptionalMachine = Optional.empty();
        } else {
            foundOptionalMachine = repository.findById(machine.getId());
        }

        if (foundOptionalMachine.isEmpty()) {
            return repository.save(machine);
        } else {
            Machine foundMachine = foundOptionalMachine.get();
            foundMachine.setNameMachine(machine.getNameMachine());
            foundMachine.setNameNetwork(machine.getNameNetwork());
            foundMachine.setDescription(machine.getDescription());
            foundMachine.setSystemVersion(machine.getSystemVersion());
            foundMachine.setEmployeesMachine(machine.getEmployeesMachine());
            return repository.save(foundMachine);
        }
    }
}


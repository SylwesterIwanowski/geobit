package pl.geobit.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String kerg;

    private String description;

    private Boolean isDeleted;

    private String startDate;

    private String endDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ApplicationUser> employees;

    @ManyToOne
    private Customer customer;

    public void addEmployee(ApplicationUser employee) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
    }
}

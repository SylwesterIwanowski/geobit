package pl.geobit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class ApplicationUser {

    public ApplicationUser() {
        isDeleted = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    @ManyToMany
    private List<Task> tasks;

    @ManyToMany
    private List<Machine> machines;

    private Boolean isDeleted;
}

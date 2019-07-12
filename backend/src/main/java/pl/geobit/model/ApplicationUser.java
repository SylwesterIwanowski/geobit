package pl.geobit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    private String role;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    @ManyToMany
    private List<Task> tasks;

    private Boolean isDeleted;
}

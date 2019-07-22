package pl.geobit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameMachine;

    private String nameNetwork;

    private String description;

    private String systemVersion;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ApplicationUser> usersMachine;




}

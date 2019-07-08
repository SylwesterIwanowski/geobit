package pl.geobit.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String kerg;

    private String description;

    private String isDelete;

    private Date startDate;

    private Date endDate;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Task> tasks;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ApplicationUser> users;

    @ManyToOne
    private Customer customer;
}

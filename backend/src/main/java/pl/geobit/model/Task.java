package pl.geobit.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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

    private String isDelete;

    private Date startDate;

    private Date endDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ApplicationUser> users;

    @ManyToOne
    private Customer customer;
 }

package model;

import javafx.scene.control.Label;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="destination")

@NamedQueries({
        @NamedQuery(name="Destination.findByName",query = "Select d from Destination d where d.name = :name"),
})

public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String name;


    @OneToMany(mappedBy = "destination",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Vacation> vacations;

    public Destination(int id, String name, List<Vacation> vacations) {
        this.id = id;
        this.name = name;
        this.vacations = vacations;

    }

    public Destination(String name) {
        this.name = name;
        this.vacations=new ArrayList<Vacation>();
    }

    public Destination() {    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }
}

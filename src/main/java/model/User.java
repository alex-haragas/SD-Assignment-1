package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name="User.findByUsername",query = "Select u from User u where u.username = :username"),
        @NamedQuery(name="User.findById",query = "Select i from User i where i.id = :id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_vacation",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="vacation_id"))
    private List<Vacation> vacation;
    public User(Integer id, String username,String password,String role,List<Vacation> vacations) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role=role;
        this.vacation =vacations;
    }

    public User(String username,String password) {
        this.username = username;
        this.password = password;
        this.role="User";
        this.vacation =new ArrayList<Vacation>();
    }

    public void addVacation(Vacation v){
        vacation.add(v);
    }

    public User() {}

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public List<Vacation> getVacation() {
        return vacation;
    }
}

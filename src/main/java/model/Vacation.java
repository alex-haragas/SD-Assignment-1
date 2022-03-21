package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vacation")

@NamedQueries({
        @NamedQuery(name = "Vacation.findByName", query = "Select v from Vacation v where v.name = :name"),
})

public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String limitB;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String period;
    @Column(nullable = false)
    private String extraDetails;


    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "vacation_id")
    private Destination destination;

    @ManyToMany(mappedBy = "vacation")
    private List<User> users;


    public Vacation() {
    }

    public Vacation(int id, String name, String limitB, String status,
                    double price, String period, String extraDetails,
                    Destination destination, List<User> users) {
        this.id = id;
        this.name = name;
        this.limitB = limitB;
        this.status = status;
        this.price = price;
        this.period = period;
        this.extraDetails = extraDetails;
        this.destination = destination;
        this.users = users;
    }


    public Vacation(String name, String limitB,
                    double price, String period, String extraDetails,
                    Destination destination) {
        this.name = name;
        this.limitB = limitB;
        this.status = "NOT_BOOKED";
        this.price = price;
        this.period = period;
        this.extraDetails = extraDetails;
        this.destination = destination;
        this.users = new ArrayList<User>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLimitB() {
        return limitB;
    }

    public String getStatus() {
        return status;
    }

    public Destination getDestination() {
        return destination;
    }

    public List<User> getUsers() {
        return users;
    }

    public double getPrice() {
        return price;
    }

    public String getPeriod() {
        return period;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setLimitB(String limitB) {
        this.limitB = limitB;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public String getDestinationName() {
        return destination.getName();
    }

    public String checkUser(User u) {
        if (users.contains(u)) return "Selected";
        else return "Not Selected";
    }

    public void addUser(User u) {
        if (status.equals("NOT_BOOKED")) {
            users.add(u);
            status = "IN_PROGRESS";
            u.getVacation().add(this);
        }
        else
        if (status.equals("IN_PROGRESS")) {
            users.add(u);
            if (users.size() == Integer.parseInt(limitB)) {
                status = "BOOKED";
            }
            u.getVacation().add(this);
        }
    }

    public void deleteUser(User u) {
        u.getVacation().remove(this);
        users.remove(u);
        if (users.size() == 0) {
            status = "NOT_BOOKED";
        } else {
            status = "IN_PROGRESS";
        }
    }
}

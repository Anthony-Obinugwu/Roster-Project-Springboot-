package unpopulardev.ActivEdge_Roster;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Roles role;
    private String specialDay;
    private String workdays = "";


    public Staff() {}
    public Staff(int id, String firstname, String lastname, Roles role, String specialDay) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.specialDay = specialDay;
    }

    public Staff(int id, String firstname, String lastname, Roles role) {
            this(id,
                firstname,
                lastname,
                role,
                null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getSpecialDay() {
        return specialDay;
    }

    public void setSpecialDay(String specialDay) {
        this.specialDay = specialDay;
    }

    public String[] getWorkdays() {
        return workdays != null ? workdays.split(",") : new String[]{};
    }

    public void setWorkdays(List<String> workdays) {
        this.workdays = String.join(",", workdays);
    }
}

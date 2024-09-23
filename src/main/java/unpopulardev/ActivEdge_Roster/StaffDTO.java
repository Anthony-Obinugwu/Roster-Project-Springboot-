package unpopulardev.ActivEdge_Roster;
public class StaffDTO {
    private String firstName;
    private String lastName;
    private String Role;

    public StaffDTO(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        Role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}

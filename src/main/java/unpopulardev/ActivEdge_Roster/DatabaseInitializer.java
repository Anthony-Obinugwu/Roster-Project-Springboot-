package unpopulardev.ActivEdge_Roster;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final StaffRepository staffRepository;

    public DatabaseInitializer(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize a map to track how many people are assigned to each day
        Map<String, Integer> dayCounts = new HashMap<>();
        List<String> allDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        for (String day : allDays) {
            dayCounts.put(day, 0);
        }

        // Predefined staff list
        List<Staff> staffList = List.of(
                new Staff(1, "Amietubodie ", "Otonye", Roles.STAFF),
                new Staff(2, "Adedamola", "Babatunde", Roles.STAFF),
                new Staff(3, "Praise", "Akobundu", Roles.STAFF),
                new Staff(4, "Smart", " Agbawo", Roles.STAFF),
                new Staff(5, "Chioma", "Nwadozie", Roles.STAFF),
                new Staff(6, "Sodeeq", " Taiwo", Roles.STAFF),
                new Staff(7, "Collins", "Eze", Roles.STAFF),
                new Staff(8, "Delight", "Emmanuel", Roles.STAFF),
                new Staff(9, "Adewale", "Favour", Roles.STAFF),
                new Staff(10, "Hammed", "Mudashir", Roles.STAFF),
                new Staff(11, "Thompson", "Idowu", Roles.STAFF),
                new Staff(12, "Ekwugha", " Elochukwu", Roles.STAFF),
                new Staff(13, "Salako", " Akinbolade", Roles.STAFF),
                new Staff(14, "Ichebadu", " Chukwu", Roles.STAFF),
                new Staff(15, "Ajah", " Kenneth", Roles.STAFF),
                new Staff(16, "Onakoya", "Kayode", Roles.STAFF),
                new Staff(17, "Jennifer", " Ewuzie", Roles.STAFF),
                new Staff(18, "Miracle", "Shaibu", Roles.STAFF),
                new Staff(19, "Christian", " Aka", Roles.STAFF),
                new Staff(20, "Ikeyi", " Ijeamaka", Roles.INTERN),
                new Staff(21, "Anthony", " Obinugwu", Roles.INTERN),
                new Staff(22, "Siyaka", "Promise", Roles.CORPER, "Thursday"),
                new Staff(23, "Kanu", "Kennedy", Roles.CORPER, "Thursday"),
                new Staff(24, "Ozeigbe", "John", Roles.CORPER, "Tuesday"),
                new Staff(25, "Godspower", "Amun", Roles.CORPER, "Tuesday")
        );

        // Assign random workdays to each staff and save
        for (Staff staff : staffList) {
            String[] workdays = assignRandomWorkdaysForRole(staff, dayCounts);
            staff.setWorkdays(List.of(workdays));
            staffRepository.save(staff);
        }
    }

    // Generate random workdays based on the staff role and available day count
    private String[] assignRandomWorkdaysForRole(Staff staff, Map<String, Integer> dayCounts) {
        List<String> availableDays = new ArrayList<>(List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

        // For Corpers, remove their special day from the available days
        if (staff.getRole() == Roles.CORPER) {
            String specialDay = staff.getSpecialDay();
            availableDays.remove(specialDay); // Remove the special day from available days
        }

        // Assign days based on role
        int limit = (staff.getRole() == Roles.INTERN || staff.getRole() == Roles.CORPER) ? 3 : 2;

        Set<String> chosenDays = new HashSet<>(); // Use a set to ensure unique days
        Collections.shuffle(availableDays); // Shuffle the available days

        for (String day : availableDays) {
            if (dayCounts.get(day) < 12 && chosenDays.size() < limit) { // Check for the 12-person limit and the required number of days
                chosenDays.add(day);
                dayCounts.put(day, dayCounts.get(day) + 1); // Increment count for the assigned day
            }
            if (chosenDays.size() == limit) break; // Stop if we've reached the desired number of days
        }
        return chosenDays.toArray(new String[0]); // Return the chosen days
    }
}

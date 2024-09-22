package unpopulardev.ActivEdge_Roster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    // Constructor injection of the repository
    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> getStaffById(int id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff addStaff(Staff staff) {
        // Check if adding this staff would exceed the daily limits
        Map<String, Integer> dayCounts = getCurrentDayCounts();
        String[] workdays = assignRandomWorkdaysForRole(staff, dayCounts);

        staff.setWorkdays(List.of(workdays)); // Set the assigned workdays
        return staffRepository.save(staff);
    }

    // Add this method to check current day counts
    private Map<String, Integer> getCurrentDayCounts() {
        Map<String, Integer> dayCounts = new HashMap<>();
        List<String> allDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

        for (String day : allDays) {
            dayCounts.put(day, 0);
            List<Staff> staffOnDay = staffRepository.findByWorkdaysContaining(day);
            dayCounts.put(day, staffOnDay.size());
        }
        return dayCounts;
    }

    // Add this method for assigning random workdays
    private String[] assignRandomWorkdaysForRole(Staff staff, Map<String, Integer> dayCounts) {
        List<String> availableDays = new ArrayList<>(List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

        if (staff.getRole() == Roles.CORPER) {
            String specialDay = staff.getSpecialDay();
            availableDays.remove(specialDay); // Remove special day from available days
        }

        int limit = (staff.getRole() == Roles.INTERN || staff.getRole() == Roles.CORPER) ? 3 : 2;
        Set<String> chosenDays = new HashSet<>(); // To ensure unique days
        Collections.shuffle(availableDays); // Shuffle for randomness

        for (String day : availableDays) {
            if (dayCounts.get(day) < 12 && chosenDays.size() < limit) { // Check for limits
                chosenDays.add(day);
                dayCounts.put(day, dayCounts.get(day) + 1);
            }
            if (chosenDays.size() == limit) break; // Stop if limit reached
        }
        return chosenDays.toArray(new String[0]); // Return chosen days
    }

    @Override
    public Staff updateStaff(int id, Staff updatedStaff) {
        Optional<Staff> existingStaff = staffRepository.findById(id);
        if (existingStaff.isPresent()) {
            Staff staff = existingStaff.get();
            staff.setFirstname(updatedStaff.getFirstname());
            staff.setLastname(updatedStaff.getLastname());
            staff.setRole(updatedStaff.getRole());
            staff.setSpecialDay(updatedStaff.getSpecialDay());
            return staffRepository.save(staff);
        } else {
            throw new RuntimeException("Staff not found with id: " + id);
        }
    }

    @Override
    public void deleteStaff(int id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
        } else {
            throw new RuntimeException("Staff not found with id: " + id);
        }
    }

    @Override
    public void deleteAllStaff() {
        staffRepository.deleteAll();
    }
}


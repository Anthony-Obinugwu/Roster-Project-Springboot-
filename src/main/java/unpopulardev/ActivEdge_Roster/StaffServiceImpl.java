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
        return staffRepository.save(staff);
    }

//    private Map<String, Integer> getCurrentDayCounts() {
//        Map<String, Integer> dayCounts = new HashMap<>();
//        List<String> allDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
//
//        for (String day : allDays) {
//            dayCounts.put(day, 0);
//            List<Staff> staffOnDay = staffRepository.findByWorkdaysContaining(day);
//            dayCounts.put(day, staffOnDay.size());
//        }
//        return dayCounts;
//    }
    public List<Staff> getStaffByWorkday(String workday) {
        return staffRepository.findByWorkdaysContaining(workday);
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


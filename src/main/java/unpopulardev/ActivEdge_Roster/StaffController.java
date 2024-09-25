package unpopulardev.ActivEdge_Roster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable int id) {
        Optional<Staff> staff = staffService.getStaffById(id);
        if (staff.isPresent()) {
            Staff staffEntity = staff.get();
            StaffDTO staffDTO = new StaffDTO(
                    staffEntity.getFirstname(),
                    staffEntity.getLastname(),
                    staffEntity.getRole().name()
            );
            return ResponseEntity.ok(staffDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/workday/{day}")
    public ResponseEntity<List<Staff>> getStaffByWorkday(@PathVariable String day) {
        List<Staff> staffList = staffService.getStaffByWorkday(day);
        if (staffList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(staffList);
    }

    @PostMapping
    public ResponseEntity<String> addStaff(@RequestBody Staff newStaff) {
        staffService.addStaff(newStaff);
        return ResponseEntity.status(HttpStatus.CREATED).body("Staff member added successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable int id, @RequestBody Staff staff) {
        try {
            return ResponseEntity.ok(staffService.updateStaff(id, staff));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        try {
            staffService.deleteStaff(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllStaff() {
        staffService.deleteAllStaff();
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DayLimitExceededException.class)
    public ResponseEntity<String> handleDayLimitExceeded(DayLimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

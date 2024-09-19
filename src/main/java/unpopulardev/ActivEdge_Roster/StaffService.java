package unpopulardev.ActivEdge_Roster;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> getAllStaff();
    Optional<Staff> getStaffById(int id);
    Staff addStaff(Staff staff);
    Staff updateStaff(int id, Staff updatedStaff);
    void deleteStaff(int id);
}


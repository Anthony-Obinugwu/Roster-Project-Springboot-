package unpopulardev.ActivEdge_Roster;

import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();
    Staff getStaffById(int id);
    Staff addStaff(Staff staff);
    Staff updateStaff(int id, Staff updatedStaff);
    void deleteStaff(int id);
    void deleteAllStaff();
}


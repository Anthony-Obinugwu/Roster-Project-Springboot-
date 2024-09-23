package unpopulardev.ActivEdge_Roster;


import java.util.List;

public interface Functions {
    void StaffAssign(List<Staff> StaffList, List<String> workdays);
    void InternAssign(List<Staff> StaffList, List<String> days);
    void CorperAssign(List<Staff> StaffList, List<String> Days);
}


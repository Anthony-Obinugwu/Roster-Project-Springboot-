package unpopulardev.ActivEdge_Roster;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Functions_Impl implements Functions {

    private static final List<String> VALID_WORKDAYS = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

    private Map<String, Integer> dayCount = new HashMap<>();

    public Functions_Impl() {
        for (String day : VALID_WORKDAYS) {
            dayCount.put(day, 0);
        }
    }
    private void assignDays(List<Staff> staffList, List<String> workdays, Roles role, int dayLimit, int personLimitPerDay) {
        if (workdays == null || workdays.isEmpty()) {
            workdays = new ArrayList<>(VALID_WORKDAYS);
        }

        for (Staff staff : staffList) {
            if (staff.getRole().equals(role)) {
                List<String> availableDays = new ArrayList<>(workdays);

                if (role.equals(Roles.CORPER) && staff.getSpecialDay() != null) {
                    availableDays.remove(staff.getSpecialDay());
                }
                Collections.shuffle(availableDays);

                List<String> assignedDays = new ArrayList<>();
                for (String day : availableDays) {
                    if (dayCount.get(day) < personLimitPerDay && assignedDays.size() < dayLimit) {
                        assignedDays.add(day);
                        dayCount.put(day, dayCount.get(day) + 1);
                    }
                }
                staff.setWorkdays(assignedDays);
                System.out.println(staff.getFirstname() + " " + staff.getLastname() + " is assigned to work on: " + assignedDays);
            }
        }
    }
    @Override
    public void StaffAssign(List<Staff> StaffList, List<String> workdays) {
        assignDays(StaffList, workdays, Roles.STAFF, 2, 12);
    }

    @Override
    public void InternAssign(List<Staff> StaffList, List<String> workdays) {
        assignDays(StaffList, workdays, Roles.INTERN, 3, 12);
    }

    @Override
    public void CorperAssign(List<Staff> StaffList, List<String> workdays) {
        assignDays(StaffList, workdays, Roles.CORPER, 3, 12);
    }

}

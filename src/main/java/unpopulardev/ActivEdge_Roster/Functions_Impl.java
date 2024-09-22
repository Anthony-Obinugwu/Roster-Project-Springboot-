package unpopulardev.ActivEdge_Roster;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Functions_Impl implements Functions {

    private static final List<String> VALID_WORKDAYS = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

    @Override
    public void StaffAssign(List<Staff> StaffList, List<String> workdays) {
        if (workdays == null || workdays.isEmpty()) {
            workdays = VALID_WORKDAYS;
        }

        Map<String, Integer> dayCount = new HashMap<>();
        for (String day : workdays) {
            dayCount.put(day, 0);
        }

        for (Staff staff : StaffList) {
            if (staff.getRole().name().equals("STAFF")) {
                List<String> availableDays = new ArrayList<>(workdays);
                Collections.shuffle(availableDays);

                List<String> assignedDays = new ArrayList<>();
                for (String day : availableDays) {
                    // Assign only two days and make sure each day is not overcrowded (max 8 staff)
                    if (dayCount.get(day) < 8 && assignedDays.size() < 2) {
                        assignedDays.add(day);
                        dayCount.put(day, dayCount.get(day) + 1);
                    }
                }

                System.out.println(staff.getFirstname() + " " + staff.getLastname() + " is assigned to work on: " + assignedDays);
            }
        }
    }

    @Override
    public void InternAssign(List<Staff> StaffList, List<String> days) {
        if (days == null || days.isEmpty()) {
            days = VALID_WORKDAYS;
        }

        Map<String, Integer> dayCount = new HashMap<>();
        for (String day : days) {
            dayCount.put(day, 0);
        }

        for (Staff staff : StaffList) {
            if (staff.getRole().name().equals("INTERN")) {
                List<String> availableDays = new ArrayList<>(days);
                Collections.shuffle(availableDays);

                List<String> assignedDays = new ArrayList<>();
                for (String day : availableDays) {
                    // Assign three days and limit interns to a max of 3 per day
                    if (dayCount.get(day) < 3 && assignedDays.size() < 3) {
                        assignedDays.add(day);
                        dayCount.put(day, dayCount.get(day) + 1);
                    }
                }

                System.out.println(staff.getFirstname() + " " + staff.getLastname() + " is assigned to work on: " + assignedDays);
            }
        }
    }

    @Override
    public void CorperAssign(List<Staff> StaffList, List<String> Days) {
        if (Days == null || Days.isEmpty()) {
            Days = VALID_WORKDAYS;
        }

        Map<String, Integer> dayCount = new HashMap<>();
        for (String day : Days) {
            dayCount.put(day, 0);
        }

        for (Staff staff : StaffList) {
            if (staff.getRole().name().equals("CORPER")) {
                List<String> availableDays = new ArrayList<>(Days);

                // Exclude the special CDS day if it exists
                String specialDay = staff.getSpecialDay();
                if (specialDay != null && availableDays.contains(specialDay)) {
                    availableDays.remove(specialDay);
                }

                Collections.shuffle(availableDays);

                List<String> assignedDays = new ArrayList<>();
                for (String day : availableDays) {
                    // Assign three days and limit corpers to 3 per day
                    if (dayCount.get(day) < 3 && assignedDays.size() < 3) {
                        assignedDays.add(day);
                        dayCount.put(day, dayCount.get(day) + 1);
                    }
                }

                System.out.println(staff.getFirstname() + " " + staff.getLastname() + " is assigned to work on: " + assignedDays + ". CDS day: " + specialDay);
            }
        }
    }

}

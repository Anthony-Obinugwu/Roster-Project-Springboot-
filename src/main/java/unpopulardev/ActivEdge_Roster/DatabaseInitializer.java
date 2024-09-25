package unpopulardev.ActivEdge_Roster;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final StaffRepository staffRepository;
    private final Functions_Impl functions;

    public DatabaseInitializer(StaffRepository staffRepository, Functions_Impl functions) {
        this.staffRepository = staffRepository;
        this.functions = functions;
    }

    @Override
    public void run(String... args) throws Exception {

        Map<String, Integer> dayCounts = new HashMap<>();
        List<String> allDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        for (String day : allDays) {
            dayCounts.put(day, 0);
        }

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

        functions.StaffAssign(staffList, null);
        functions.InternAssign(staffList, null);
        functions.CorperAssign(staffList, null);

        for (Staff staff : staffList) {
            staffRepository.save(staff);
        }
    }
}

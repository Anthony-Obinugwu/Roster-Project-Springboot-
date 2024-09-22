package unpopulardev.ActivEdge_Roster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findByWorkdaysContaining(String workday);
}

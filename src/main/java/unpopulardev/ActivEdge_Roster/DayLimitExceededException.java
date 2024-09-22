package unpopulardev.ActivEdge_Roster;

public class DayLimitExceededException extends RuntimeException {
    public DayLimitExceededException(String message) {
        message = "There ain't any more days for this person cuh";
    }
}

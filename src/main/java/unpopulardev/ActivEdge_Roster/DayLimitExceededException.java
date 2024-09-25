package unpopulardev.ActivEdge_Roster;
public class DayLimitExceededException extends Exception {
    public DayLimitExceededException() {
        super("Please select another day; you're going to overcrowd the office.");
    }
    public DayLimitExceededException(String message) {
        super(message);
    }
}

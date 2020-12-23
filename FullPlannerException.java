
//  FullPlannerException class
//  custom exception class that is called when the user is trying to add a
//  course into a full planner.

public class FullPlannerException extends Exception {
    public FullPlannerException(String message) {
        super(message);
    }
}

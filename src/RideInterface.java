/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * The amusement facility interface defines the core functions that the amusement facility needs to achieve
 * It includes operations such as queue management, history record management, and running a cycle
 */
public interface RideInterface {
    /**
     * Add visitors to the waiting queue
     * @param visitor TThe visitor objects to be added must include complete visitor information (name, ID, membership type, etc.)
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * Remove from the waiting queue and return to the head of the queue as a visitor
     * @return The removed visitor object returns null when the queue is empty
     */
    Visitor removeVisitorFromQueue();

    /**
     * Print the detailed information of the current waiting queue
     */
    void printQueue();

    /**
     * Add visitors to the ride history
     * @param visitor Visitors who have visited
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * Check whether the visitor is in the history of the ride (determined uniquely by the visitor ID)
     * @param visitor Visitor subjects to be inspected (with valid visitor ids)
     * @return If it exists, return true; otherwise, return false
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * Obtain the total number of visitors in the ride history (including repeated visits)
     * @return Total number of visitors
     */
    int numberOfVisitors();

    /**
     * Print the ride history (including statistical information)
     */
    void printRideHistory();

    /**
     * One cycle of operating the ride (handling the part waiting for visitors and adding to history)
     */
    void runOneCycle();
}
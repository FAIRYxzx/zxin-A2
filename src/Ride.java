import java.util.Queue;
import java.util.LinkedList;

public class Ride {
    private Queue<Visitor> waitingLine;
    private String rideName;
    private String rideParkArea;
    private String rideType;
    private Employee operator;

    public Ride() {
        this.rideName = "Unnamed facility";
        this.rideParkArea = "Unspecified park";
        this.rideType = "No specified type";
        this.operator = null;
        this.waitingLine = new LinkedList<>();
    }


    public Ride(String rideName, String rideParkArea, String rideType, Employee operator) {

        this.setRideName(rideName);
        this.setRideParkArea(rideParkArea);
        this.setRideType(rideType);
        this.setOperator(operator);
        this.waitingLine = new LinkedList<>();
    }

    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Failed to add: The visitor object is empty and cannot be added to the queue!");
            return;
        }
        if (visitor.getVisitorID() == null || visitor.getVisitorID().trim().isEmpty()) {
            System.out.println("Failed to add: The visitor ID was not set, and they cannot be added to the [" + this.rideName + "] queue！");
            return;
        }
        boolean isAdded = waitingLine.offer(visitor);
        if (isAdded) {
            System.out.println("Successful addition: Visitor [" + visitor.getName() + "](ID：" + visitor.getVisitorID() + ")joins the [" + this.rideName + "] waiting queue");
        } else {
            System.out.println("Failed to add: visitor [" + visitor.getName() + "] failed to join the [" + this.rideName + "] queue. Please try again!");
        }
    }

    public Visitor removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("Failure to remove：[" + this.rideName + "] waiting queue is empty, unable to remove visitor！");
            return null;
        }

        Visitor removedVisitor = waitingLine.poll();
        System.out.println("Remove success：Visitor [" + removedVisitor.getName() + "](ID：" + removedVisitor.getVisitorID() + ") from the queue[" + this.rideName + "] removed");
        return removedVisitor;
    }

    public void printQueue() {
        System.out.println("\n===== [" + this.rideName + "] Details of the waiting queue =====");
        System.out.println("Current number of visitor waiting：" + waitingLine.size());
        if (waitingLine.isEmpty()) {
            System.out.println("📭There are no waiting visitors at present");
            System.out.println("====================================\n");
            return;
        }
        int visitorIndex = 1;
        for (Visitor visitor : waitingLine) {
            System.out.println(visitorIndex + ". Name：" + visitor.getName()
                    + " | MembershipType：" + visitor.getMembershipType()
                    + " | Visitor ID：" + visitor.getVisitorID()
                    + " | Age：" + visitor.getAge());
            visitorIndex++;
        }
        System.out.println("====================================\n");
    }

    public Queue<Visitor> getWaitingQueue() {
        return waitingLine;
    }

    public String getRideName() {
        return rideName;
    }

    public String getRideParkArea() {
        return rideParkArea;
    }

    public String getRideType() {
        return rideType;
    }

    public Employee getOperator() {
        return operator;
    }


    public void setRideName(String rideName) {
        if (rideName != null && !rideName.trim().isEmpty()) {
            this.rideName = rideName;
            System.out.println("The name of the ride is set to：" + rideName);
        } else {
            System.out.println("Error: The names of ride cannot be empty！");
        }
    }

    public void setRideParkArea(String rideParkArea) {
        if (rideParkArea == null || rideParkArea.trim().isEmpty()) {
            System.out.println("Error: The park where the ride belong cannot be empty！");
            return;
        }

        if (this.operator != null) {
            String operatorPark = this.operator.getParkArea();

            if (operatorPark == null || operatorPark.trim().isEmpty()) {
                System.out.println("Warning: The operator[" + this.operator.getName() + "]has not yet set the affiliated campus. Consistency verification is not conducted for the time being！");
            } else if (!operatorPark.equals(rideParkArea)) {
                System.out.println("Error: The park where the ride belongs [" + rideParkArea + "]does not match the park where the operator belongs [" + operatorPark + "]and thus cannot be set up！");
                return;
            }
        }


        this.rideParkArea = rideParkArea;
        System.out.println("The park where the ride belongs is set to：" + rideParkArea);
    }

    public void setRideType(String rideType) {
        if (rideType == null || rideType.trim().isEmpty()) {
            System.out.println("Error: The type of rides cannot be empty！");
            return;
        }
        if (rideType.equalsIgnoreCase("Kids Rides") ||
                rideType.equalsIgnoreCase("Family Rides") ||
                rideType.equalsIgnoreCase("Thrill Rides") ||
                rideType.equalsIgnoreCase("Science Rides")) {
            this.rideType = rideType;
            System.out.println("The ride type is set to：" + rideType);
        } else {
            System.out.println("Error: Invalid type of rides！");
            System.out.println("The available types are: Kids Rides, Family Rides, Thrill Rides, and Science Rides");
        }
    }


    public void setOperator(Employee operator) {

        if (operator == null) {
            this.operator = null;
            System.out.println("The rides have no operators and are temporarily closed!");
            return;
        }

        String operatorPark = operator.getParkArea();
        if (operatorPark == null || operatorPark.trim().isEmpty()) {
            System.out.println("Error: The operator[" + operator.getName() + "]has not yet set the affiliated park and cannot be associated with the rides！");
            return;
        }


        String ridePark = this.rideParkArea;
        if (ridePark == null || ridePark.trim().isEmpty() || "Unspecified park".equals(ridePark)) {
            System.out.println("Error: The rides have not yet been assigned to a specific park and thus cannot be associated with an operator[" + operator.getName() + "]！");
            return;
        }

        if (!operatorPark.equals(ridePark)) {
            System.out.println("Error: The operator's affiliated park is not the same as the ride's affiliated park and cannot be associated!");
            return;
        }

        this.operator = operator;
        System.out.println("The operator of the ride is set to：" + operator.getName() + "（Employee ID：" + operator.getEmployeeId() + "，Affiliated park：" + operatorPark + "）");
    }

    @Override
    public String toString() {
        String parkConsistency = (operator != null && operator.getParkArea() != null)
                ? (operator.getParkArea().equals(rideParkArea) ? "[Consistent Park]" : "[Inconsistent park]")
                : "[Unvalidated]";

        return "=== Details of Rides ===" + "\n" +
                "Name：" + rideName + "\n" +
                "Affiliated park：" + rideParkArea + "\n" +
                "Type：" + rideType + "\n" +
                "Operator：" + (operator != null ? operator.getName() + "（park：" + operator.getParkArea() + "）" : "Unvalidated");
    }
}

public class Ride {

    private String rideName;
    private String rideParkArea;
    private String rideType;
    private Employee operator;

    public Ride() {
        this.rideName = "Unnamed facility";
        this.rideParkArea = "Unspecified park";
        this.rideType = "No specified type";
        this.operator = null;
    }


    public Ride(String rideName, String rideParkArea, String rideType, Employee operator) {

        this.setRideName(rideName);
        this.setRideParkArea(rideParkArea);
        this.setRideType(rideType);
        this.setOperator(operator);
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
                "Operator：" + (operator != null ? operator.getName() + "（park：" + operator.getParkArea() + "）" : "Unvalidated") + "\n" +
                "园区一致性：" + parkConsistency;
    }
}
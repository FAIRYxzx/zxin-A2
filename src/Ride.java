/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

import java.util.*;
import java.io.*;

/**
 * Ride class that implements RideInterface
 */
public class Ride implements  RideInterface{
    // Waiting queue (storing visitors waiting to visit)
    private Queue<Visitor> waitingLine;
    //Name of Ride
    private String rideName;
    //The affiliated park
    private String rideParkArea;
    //Facility type (one of the four types: Kids Rides, Family Rides, Thrill Rides, and Science Rides)
    private String rideType;
    //Operator (Employee object)
    private Employee operator;
    // Visit History Record (Stores all visitors who have visited, including duplicates)
    private LinkedList<Visitor> rideHistory;
    //Maximum passenger capacity per cycle
    private int maxRider;
    //The number of running cycles (Initially set to 0)
    private int numOfCycles = 0;

    /**
     * Parameterless constructor, initializing default values
     */
    public Ride() {
        this.rideName = "Unnamed facility";
        this.rideParkArea = "Unspecified park";
        this.rideType = "No specified type";
        this.operator = null;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    /**
     * The parameterized construction method initializes the basic information of the rides
     * @param rideName Name of ride
     * @param rideParkArea The affiliated park
     * @param rideType Ride type
     * @param operator Ride Operator
     */
    public Ride(String rideName, String rideParkArea, String rideType, Employee operator) {

        this.setRideName(rideName);
        this.setRideParkArea(rideParkArea);
        this.setRideType(rideType);
        this.setOperator(operator);
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    /**
     * Add visitors to the waiting queue (including parameter verification)
     * @param visitor The visitor object to be added
     */
    public void addVisitorToQueue(Visitor visitor) {
        //Verify whether the visitor object is empty (basic non-empty check)
        if (visitor == null) {
            System.out.println("Failed to add: The visitor object is empty and cannot be added to the queue!");
            return;
        }
        //Verify whether the visitor ID is valid (The visitor ID is the unique identifier of the visitor)
        if (visitor.getVisitorID() == null || visitor.getVisitorID().trim().isEmpty()) {
            System.out.println("Failed to add: The visitor ID was not set, and they cannot be added to the [" + this.rideName + "] queue！");
            return;
        }
        //Add to the queue using offer
        boolean isAdded = waitingLine.offer(visitor);
        if (isAdded) {
            System.out.println("Successful addition: Visitor [" + visitor.getName() + "](ID：" + visitor.getVisitorID() + ")joins the [" + this.rideName + "] waiting queue");
        } else {
            System.out.println("Failed to add: visitor [" + visitor.getName() + "] failed to join the [" + this.rideName + "] queue. Please try again!");
        }
    }

    /**
     * Remove from the waiting queue and return to the head of the queue as a visitor
     * @return For removed visitors, return null when the queue is empty
     */
    public Visitor removeVisitorFromQueue() {
        //Check whether the queue is empty
        if (waitingLine.isEmpty()) {
            System.out.println("Failure to remove：[" + this.rideName + "] waiting queue is empty, unable to remove visitor！");
            return null;
        }
        //Remove and return the element at the head of the queue
        Visitor removedVisitor = waitingLine.poll();
        System.out.println("Remove success：Visitor [" + removedVisitor.getName() + "](ID：" + removedVisitor.getVisitorID() + ") from the queue[" + this.rideName + "] removed");
        return removedVisitor;
    }

    /**
     * Print the detailed information of the waiting queue (number of people, information of each visitor)
     */
    public void printQueue() {
        System.out.println("\n===== [" + this.rideName + "] Details of the waiting queue =====");
        System.out.println("Current number of visitor waiting：" + waitingLine.size());
        //Empty queue processing
        if (waitingLine.isEmpty()) {
            System.out.println("There are no waiting visitors at present");
            System.out.println("====================================\n");
            return;
        }
        //Traverse the queue and output the visitor information in sequence (the sequence numbers start from 1)
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

    /**
     * Add visitors to the visit history (including parameter verification)
     * @param visitor Visitors who have visited
     */
    public void addVisitorToHistory(Visitor visitor) {
        // Verify whether the visitor object is empty
        if (visitor == null) {
            System.out.println("Failed to add to history: Visitor object is null!");
            return;
        }
        // Extract the visitor ID and verify its validity
        String visitorId = visitor.getVisitorID();
        if (visitorId == null || visitorId.trim().isEmpty()) {
            System.out.println("Failed to add to history: Visitor ID is not set!");
            return;
        }
        // Add to the history
        rideHistory.add(visitor);
        System.out.println("Successfully added to history: Visitor [" + visitor.getName() + "] (ID:" + visitorId + ") | Total rides now: " + numberOfVisitors());
    }

    /**
     * Check whether the visitor is in the travel history (judged by the visitor ID)
     * @param visitor Visitor subjects to be inspected
     * @return Return true if it exists; otherwise, return false
     */
    public boolean checkVisitorFromHistory(Visitor visitor) {
        // Parameter validity verification
        if (visitor == null || visitor.getVisitorID() == null) {
            System.out.println("Error: Invalid visitor (null or no ID) for history check!");
            return false;
        }
        // Traverse the history record (using Iterator)
        Iterator<Visitor> iterator = rideHistory.iterator();
        while (iterator.hasNext()) {
            // Match by VisitorID (Visitor ID is the unique identifier of the visitor)
            if (iterator.next().getVisitorID().equals(visitor.getVisitorID())) {
                System.out.printf("Check Result: Visitor [%s] (ID: %s) is in the ride history.\n",
                        visitor.getName(), visitor.getVisitorID());
                return true;
            }
        }

        //Unmatched prompt
        System.out.printf("Check Result: Visitor [%s] (ID: %s) IS NOT in the ride history.\n",
                visitor.getName(), visitor.getVisitorID());
        return false;
    }

    /**
     * Obtain the total number of records in the ride history (including repeated plays)
     * @return The total number of historical records
     */
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    /**
     * Print the travel history and statistical information
     */
    public void printRideHistory() {
        System.out.println("\n===== [" + this.rideName + "] Ride History (Total Ride times = " + numberOfVisitors() + ") =====");
        System.out.println("Total ride times (all records): " + numberOfVisitors());
        System.out.println("Number of unique visitors (by ID): " + getUniqueVisitorCount());
        System.out.println("------------------------------------");

        //Processing of empty history
        if (rideHistory.isEmpty()) {
            System.out.println("No visitors in ride history yet.");
            System.out.println("====================================\n");
            return;
        }
        //Store the unique visitor (the visitor ID is the key to avoid repetition)
        Map<String, Visitor> uniqueVisitorMap = new LinkedHashMap<>();
        //Store the number of visits for each visitor (the visitor ID is the key, and the number of visits is the value)
        Map<String, Integer> visitCountMap = new LinkedHashMap<>();
        Iterator<Visitor> iterator = rideHistory.iterator();
        //Go through history and count the number of visits each visitor has made
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            String visitorId = visitor.getVisitorID();
            //If you are a new visitor, add it to the Map of the only visitor
            if (!uniqueVisitorMap.containsKey(visitorId)) {
                uniqueVisitorMap.put(visitorId, visitor);
            }
            visitCountMap.put(visitorId, visitCountMap.getOrDefault(visitorId, 0) + 1);
        }

        //Output detailed statistics of each visitor
        int recordIndex = 1;
        for (Map.Entry<String, Integer> entry : visitCountMap.entrySet()) {
            String visitorId = entry.getKey();
            Visitor uniqueVisitor = uniqueVisitorMap.get(visitorId); //Get detailed information about visitors
            int visitCount = entry.getValue(); //Get play counts

            //Formatted output
            System.out.printf(recordIndex + ". Name: " + uniqueVisitor.getName()
                            + " | Membership Type: " + uniqueVisitor.getMembershipType()
                            + " | Visitor ID: " + uniqueVisitor.getVisitorID()
                            + " | Age: " + uniqueVisitor.getAge()
                            + " | Visit Count: " + visitCount+"\n");
            recordIndex++;
        }

        System.out.println("====================================\n");
    }

    /**
     * Sort the ride history by membership type and age (membership type first, then age)
     */
    public void sortRideHistory() {
        //Empty history verification
        if (rideHistory.isEmpty()) {
            System.out.println("Error: Cannot sort ride history, the history is empty!");
            return;
        }
        //Sorting (Comparator is implemented by VisitorComparator)
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("Successfully sorted ride history of [" + this.rideName + "]!");
    }

    /**
     * One cycle of operating rides
     */
    public void runOneCycle(){
        //Verify whether the operator has been assigned
        if (operator == null) {
            System.out.println("Error: The [" + rideName + "] cannot operate without an operator assigned！");
            return;
        }
        //Verify whether the waiting queue is empty
        if (waitingLine.isEmpty()) {
            System.out.println("Error: The [" + rideName + "] waiting queue is empty and cannot run！");
            return;
        }
        //Verify whether the passenger capacity is valid
        if (maxRider < 1) {
            System.out.println("Error: The [" + rideName + "] cannot operate without an effective visitor capacity (≥1 person) set！");
            return;
        }
        //Calculate the actual passenger capacity (whichever is the maximum passenger capacity or the smaller number of people in the waiting queue)
        int actualRiders = Math.min(maxRider, waitingLine.size());
        System.out.println("Maximum visitor capacity for this Ride：" + maxRider + " | Remaining in the waiting queue：" + waitingLine.size() + " | Actual visitor capacity：" + actualRiders + "\n");
        //Retrieve visitors from the queue and add them to the history (complete one cycle)
        for (int i = 0; i < actualRiders; i++) {
            Visitor rider = waitingLine.poll();
            addVisitorToHistory(rider);
        }
        //Update the number of running cycles
        numOfCycles++;
        //Output the result of the periodic operation
        System.out.println("=== The [" + rideName + "] round run of  " + numOfCycles + " has ended ===");
        System.out.println("The current waiting queue is remaining：" + waitingLine.size() + " | Cumulative running times：" + numOfCycles + "\n");
    }

    /**
     * Export the play history as a CSV file
     * @param filePath The path of the exported file
     */
    public void exportRideHistory(String filePath) {
        //Empty history verification
        if (rideHistory.isEmpty()) {
            System.out.println("Error: The ride history is empty and no data can be exported！");
            return;
        }
        //Count the number of visits each visitor has made (grouped by VisitorID)
        Map<String, Integer> visitCountMap = new LinkedHashMap<>();
        for (Visitor visitor : rideHistory) {
            String visitorId = visitor.getVisitorID();
            visitCountMap.put(visitorId, visitCountMap.getOrDefault(visitorId, 0) + 1);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            //Write to the CSV table header
            writer.write("Name,Age,Phone,ID,Visitor ID,Membership Type,Number of runs");
            writer.newLine();
            //Traverse the statistical results and write each line of data
            for (Map.Entry<String, Integer> entry : visitCountMap.entrySet()) {
                String visitorId = entry.getKey();
                int rideCount = entry.getValue();

                Visitor targetVisitor = null;
                for (Visitor v : rideHistory) {
                    if (v.getVisitorID().equals(visitorId)) {
                        targetVisitor = v;
                        break;
                    }
                }
                if (targetVisitor == null) continue;
                //Handle null values (replace them with empty strings to avoid CSV format errors)
                String name = targetVisitor.getName() != null ? targetVisitor.getName() : "";
                String age = String.valueOf(targetVisitor.getAge());
                String phone = targetVisitor.getPhone() != null ? targetVisitor.getPhone() : "";
                String id = targetVisitor.getID() != null ? targetVisitor.getID() : "";
                String visitorID = targetVisitor.getVisitorID() != null ? targetVisitor.getVisitorID() : "";
                String membership = targetVisitor.getMembershipType() != null ? targetVisitor.getMembershipType() : "";
                //Concatenate CSV lines (separated by commas)
                String csvLine = String.join(
                        ",",
                        name, age, phone, id, visitorID, membership, String.valueOf(rideCount)
                );
                writer.write(csvLine);
                writer.newLine();
            }

            System.out.println("The history of rides has been successfully exported!\n" + "File path：" + filePath);
        } catch (IOException e) {
            System.out.println("Export failed：" + e.getMessage());
        }
    }

    /**
     * Import the play history from the CSV file
     * @param filePath The path of the imported file
     * @param append Whether to append to the existing history (true: append; false: Overwrite)
     */
    public void importRideHistory(String filePath,boolean append) {
        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            System.out.println("Import failed: The file does not exist in the specified path");
            return;
        }

        int successCount = 0; //The number of successfully imported records (initially 0)
        int failCount = 0; //Number of failure records (initially 0)
        rideHistory.clear();
        Visitor.clearUsedIds();
        //If no additional is added, the existing history and used visitor ids will be cleared
        if (!append) {
            rideHistory.clear();
            Visitor.clearUsedIds();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean skipHeader = true; //Skip the header row

            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                //Split the CSV rows (-1 to ensure that empty fields are not ignored)
                String[] fields = line.split(",", -1);
                if (fields.length != 7) { //Check the number of fields (must be 7)
                    System.out.println("Skip invalid lines (incorrect number of fields, 7 fields required) ：" + line);
                    failCount++;
                    continue;
                }
                //Extract the fields and trim the blank Spaces
                String name = fields[0].trim();
                String ageStr = fields[1].trim();
                String phone = fields[2].trim();
                String id = fields[3].trim();
                String visitorID = fields[4].trim();
                String membership = fields[5].trim();
                String rideCountStr = fields[6].trim();

                //Verify that the required fields are not blank
                if (name.isEmpty() || visitorID.isEmpty() || membership.isEmpty() || rideCountStr.isEmpty()) {
                    System.out.println("Skip invalid lines (required fields are left blank)：" + line);
                    failCount++;
                    continue;
                }

                //Verify age format (integer)
                int age;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException e) {
                    System.out.println("Skip invalid lines (age format error)：" + line);
                    failCount++;
                    continue;
                }

                //Verify the format of the number of visits (integer and ≥1)
                int rideCount;
                try {
                    rideCount = Integer.parseInt(rideCountStr);
                    if (rideCount < 1) rideCount = 1;  //The mandatory minimum is 1
                } catch (NumberFormatException e) {
                    System.out.println("Skip invalid lines (incorrect format for the number of rides)：" + line);
                    failCount++;
                    continue;
                }
                //Create and set up visitor information
                Visitor visitor = new Visitor();
                visitor.setName(name);
                visitor.setAge(age);
                visitor.setPhone(phone);
                visitor.setID(id);
                visitor.setMembershipType(membership);
                visitor.setVisitorID(visitorID);

                //Verify whether the visitor ID and membership type are valid
                //Add to history by play count (repeat adding rideCount times)
                if (visitor.getVisitorID() != null && visitor.getMembershipType() != null) {
                    for (int i = 0; i < rideCount; i++) {
                        rideHistory.add(visitor);
                        successCount++;
                    }
                } else {
                    System.out.println("Skip invalid visitor data：" + line);
                    failCount++;
                }
            }

            System.out.println("Import completed！Success: " + successCount + "  records | Failure：" + failCount + " records");
        } catch (IOException e) {
            System.out.println("Import failed：" + e.getMessage());
        }
    }

    /**
     * Parameterless import method (appended to the existing history by default)
     * @param filePath The path of the imported file
     */
    public void importRideHistory(String filePath) {
        importRideHistory(filePath, true);
    }

    /**
     * Obtain the number of unique visitors (de-duplicated by visitor ID)
     * @return The number of unique visitors
     */
    public int getUniqueVisitorCount() {
        Set<String> uniqueVisitorIds = new HashSet<>();
        Iterator<Visitor> iterator = rideHistory.iterator();
        while (iterator.hasNext()) {
            uniqueVisitorIds.add(iterator.next().getVisitorID());
        }
        return uniqueVisitorIds.size();
    }
    //Getter and Setter methods
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

    public int getMaxRider() {
        return maxRider;
    }

    /**
     * Set the maximum passenger capacity per cycle (it must be ≥1)
     * @param maxRider Maximum passenger capacity
     */
    public void setMaxRider(int maxRider) {
        if (maxRider >= 1) {
            this.maxRider = maxRider;
            System.out.println("[" + rideName + "] The maximum visitor capacity at a time is set to：" + maxRider);
        } else {
            System.out.println("Error: The maximum single visitor capacity must be at least one person！");
        }
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    /**
     * Set the name of the ride (non-empty verification)
     * @param rideName Ride name
     */
    public void setRideName(String rideName) {
        if (rideName != null && !rideName.trim().isEmpty()) {
            this.rideName = rideName;
            System.out.println("The name of the ride is set to：" + rideName);
        } else {
            System.out.println("Error: The names of ride cannot be empty！");
        }
    }

    /**
     * Set up the affiliated park (including consistency verification with the operator's park)
     * @param rideParkArea Name of the park
     */
    public void setRideParkArea(String rideParkArea) {
        //Verify that the park is not empty
        if (rideParkArea == null || rideParkArea.trim().isEmpty()) {
            System.out.println("Error: The park where the ride belong cannot be empty！");
            return;
        }
        //If an operator has been set up, verify the consistency of the park
        if (this.operator != null) {
            String operatorPark = this.operator.getParkArea();

            if (operatorPark == null || operatorPark.trim().isEmpty()) {
                System.out.println("Warning: The operator[" + this.operator.getName() + "]has not yet set the affiliated campus. Consistency verification is not conducted for the time being！");
            } else if (!operatorPark.equals(rideParkArea)) {
                System.out.println("Error: The park where the ride belongs [" + rideParkArea + "]does not match the park where the operator belongs [" + operatorPark + "]and thus cannot be set up！");
                return;
            }
        }

        //Verification passed. Set the affiliated park
        this.rideParkArea = rideParkArea;
        System.out.println("The park where the ride belongs is set to：" + rideParkArea);
    }

    /**
     * Set the ride type (it must be one of the specified four types)
     * @param rideType  Ride type
     */
    public void setRideType(String rideType) {
        if (rideType == null || rideType.trim().isEmpty()) {
            System.out.println("Error: The type of rides cannot be empty！");
            return;
        }
        //Verify whether the type is one of the four allowed types (case-insensitive)
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

    /**
     * Set up operators (including consistency verification with the park to which the facility belongs)
     * @param operator operator object
     */
    public void setOperator(Employee operator) {
        // Allow setting to empty (indicating no operator)
        if (operator == null) {
            this.operator = null;
            System.out.println("The rides have no operators and are temporarily closed!");
            return;
        }
        //The verification operator's affiliated park has been set up
        String operatorPark = operator.getParkArea();
        if (operatorPark == null || operatorPark.trim().isEmpty()) {
            System.out.println("Error: The operator[" + operator.getName() + "]has not yet set the affiliated park and cannot be associated with the rides！");
            return;
        }

        //The verification facility's affiliated park has been set up
        String ridePark = this.rideParkArea;
        if (ridePark == null || ridePark.trim().isEmpty() || "Unspecified park".equals(ridePark)) {
            System.out.println("Error: The rides have not yet been assigned to a specific park and thus cannot be associated with an operator[" + operator.getName() + "]！");
            return;
        }
        //Verify the park consistency between the operator and the facility
        if (!operatorPark.equals(ridePark)) {
            System.out.println("Error: The operator's affiliated park is not the same as the ride's affiliated park and cannot be associated!");
            return;
        }
        //Verification passed. Set the operator
        this.operator = operator;
        System.out.println("The operator of the ride is set to：" + operator.getName() + "（Employee ID：" + operator.getEmployeeId() + "，Affiliated park：" + operatorPark + "）");
    }

    public LinkedList<Visitor> getRideHistory() {
        return rideHistory;
    }

    /**
     * Rewrite the toString method to return the complete information of the amusement facility
     * @return A string containing information such as name, affiliated park, type, and operator
     */
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
                "Max Riders per Cycle：" + (maxRider >= 1 ? maxRider : "Unset") + "\n" +
                "Total Cycles Run：" + numOfCycles;
    }
}
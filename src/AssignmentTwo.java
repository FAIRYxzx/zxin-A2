/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * The AssignmentTwo class is the main program class, used to demonstrate the usage of each functional module
 * It includes multiple test methods to verify functions such as visitor queues, history records, sorting, running cycles, import and export, etc
 */
public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        demo.partThree();
        demo.partFourA();
        demo.partFourB();
        demo.partFive();
        demo.partSix();
        demo.partSeven();
    }

    /**
     * Part Three: Demonstrate the operations of adding and removing visitor queues
     */
    public void partThree() {
        System.out.println("\n===== Part 3 Demonstration =====");
        // Create an operator
        Employee coasterOperator = new Employee(
                "Mike", 32, "13988889999", "110101199203156789",
                "AD12345", "Ride Operator", "Adventure Zone");
        // Create roller coaster rides
        Ride rollerCoaster = new Ride("Roller coaster", "Adventure Zone", "Thrill Rides", coasterOperator);
        // Create 5 visitors (different membership types)
        Visitor v1 = new Visitor("Jack", 25, "13411122333", "11010119900101123X", "O111111111", "Ordinary tourist");
        Visitor v2 = new Visitor("Jerry", 21, "13511122333", "310101199505154566", "V111111111", "VIP");
        Visitor v3 = new Visitor("Tom", 22, "13611122333", "440106200010207899", "S111111111", "SVIP");
        Visitor v4 = new Visitor("Lucy", 18, "13711122333", "440304198508082464", "O222222222", "Ordinary tourist");
        Visitor v5 = new Visitor("Mark", 24, "13811122333", "510104200503033577", "V222222222", "VIP");

        // Add visitors to the waiting queue
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);
        System.out.println("=== The waiting queue after the addition ===");
        rollerCoaster.printQueue();  // Print the added queue
        // Remove the visitor at the head of the queue
        rollerCoaster.removeVisitorFromQueue();
        // Print the removed queue
        System.out.println("\n=== The waiting queue of the first visitor after removal ===");
        rollerCoaster.printQueue();

    }

    /**
     * Part Four A: Demonstrate the addition, inspection and printing of the historical records of rides
     */
    public void partFourA() {
        System.out.println("\n===== Part 4A Demonstration =====");
        // Create an operator
        Employee ferrisOperator = new Employee(
                "Lisa", 28, "13877776666", "310101199605127890",
                "FA45678", "Ride Operator", "Fairy Tale Area");
        // Create a Ferris wheel amusement facility
        Ride ferrisWheel = new Ride(
                "Giant Ferris Wheel", "Fairy Tale Area", "Family Rides", ferrisOperator);
        // Create 5 visitors (different membership types)
        Visitor v1 = new Visitor("Alice", 28, "13912345678", "110101199702034567", "O333333333", "Ordinary tourist");
        Visitor v2 = new Visitor("Bob", 35, "13887654321", "310101199005127890", "V333333333", "VIP");
        Visitor v3 = new Visitor("Charlie", 19, "13765432109", "440106200508201234", "S333333333", "SVIP");
        Visitor v4 = new Visitor("Diana", 24, "13698765432", "440304199911156789", "O444444444", "Ordinary tourist");
        Visitor v5 = new Visitor("Ethan", 30, "13578901234", "510104199304059876", "V444444444", "VIP");
        // Add visitors to the ride history (including repeated addition of v2)
        System.out.println("\n=== Adding visitors to ride history ===");
        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);
        ferrisWheel.addVisitorToHistory(v4);
        ferrisWheel.addVisitorToHistory(v5);
        ferrisWheel.addVisitorToHistory(v2); // v2 Replay
        // Check if v2 is in the history
        System.out.println("\n=== Checking if Bob is in ride history ===");
        ferrisWheel.checkVisitorFromHistory(v2);
        // Print historical records (including statistical information)
        ferrisWheel.printRideHistory();
    }

    /**
     * / * *
     * Part Four B: Demonstrate the function of organizing the historical records of rides (by member type and age)
     * * /
     */
    public void partFourB() {
        System.out.println("\n===== Part 4B Demonstration =====");
        // Create an operator
        Employee flumeOperator = new Employee(
                "John", 30, "13766665555", "440106199109201234",
                "WP78901", "Ride Operator", "Water Park");
        // Create a log flume adventure ride
        Ride logFlume = new Ride(
                "Log Flume Adventure", "Water Park", "Family Rides", flumeOperator);
        // Create 5 visitors (different membership types and ages)
        Visitor v1 = new Visitor("Frank", 22, "13923456789", "110101200203045678", "O555555555", "Ordinary tourist"); // 普通，22
        Visitor v2 = new Visitor("Grace", 29, "13834567890", "310101199506157890", "V555555555", "VIP"); // VIP，29
        Visitor v3 = new Visitor("Henry", 33, "13745678901", "440106199109201234", "S555555555", "SVIP"); // SVIP，33
        Visitor v4 = new Visitor("Ivy", 25, "13656789012", "440304199801156789", "V666666666", "VIP"); // VIP，25
        Visitor v5 = new Visitor("Jackie", 18, "13567890123", "510104200605059876", "S666666666", "SVIP"); // SVIP，18
        // Add visitors to history (for sorting)
        System.out.println("\n=== Adding visitors to ride history (for sorting) ===");
        logFlume.addVisitorToHistory(v1);
        logFlume.addVisitorToHistory(v2);
        logFlume.addVisitorToHistory(v3);
        logFlume.addVisitorToHistory(v4);
        logFlume.addVisitorToHistory(v5);
        logFlume.addVisitorToHistory(v2);  // v2 repeats

        // Print the history before sorting
        System.out.println("\n=== Ride History BEFORE Sorting ===");
        logFlume.printRideHistory();
        // Sort the history record
        System.out.println("\n=== Sorting ride history ===");
        logFlume.sortRideHistory();
        // Print the sorted history (in the order of SVIP > VIP > regular, and in ascending order by age for the same level)
        System.out.println("\n=== Ride History AFTER Sorting ===");
        logFlume.printRideHistory();
    }

    /**
     * Part Five: Demonstration of the Operating Cycle of Rides (Handling waiting queues and Updating History)
     */
    public void partFive() {
        System.out.println("\n===== Part 5 Demonstration =====");
        // Create an operator
        Employee coasterOperator = new Employee(
                "Mike Wilson", 32, "13988889999", "110101199203156789",
                "AD12345", "Ride Operator", "Adventure Zone");
        // Create a jungle train ride
        Ride jungleTrain = new Ride(
                "Jungle Train", "Adventure Zone", "Family Rides", coasterOperator);
        jungleTrain.setMaxRider(4);  //Set the maximum passenger capacity per cycle to 4
        // Create 10 visitors
        Visitor[] part5Visitors = new Visitor[]{
                new Visitor("Amy Chen", 22, "13512345678", "110101200304051234", "O666666666", "Ordinary tourist"),
                new Visitor("Bob Li", 28, "13612345678", "310101199704051234", "V000000000", "VIP"),
                new Visitor("Cindy Wang", 25, "13712345678", "440101200004051234", "O777777777", "Ordinary tourist"),
                new Visitor("David Zhang", 30, "13812345678", "510101199504051234", "S000000000", "SVIP"),
                new Visitor("Ella Zhao", 24, "13912345678", "110101200104051234", "O888888888", "Ordinary tourist"),
                new Visitor("Frank Liu", 27, "13523456789", "310101199804051234", "V777777777", "VIP"),
                new Visitor("Grace He", 23, "13623456789", "440101200204051234", "O999999999", "Ordinary tourist"),
                new Visitor("Henry Sun", 29, "13723456789", "510101199604051234", "S777777777", "SVIP"),
                new Visitor("Ivy Ma", 26, "13823456789", "110101199904051234", "O000000000", "Ordinary tourist"),
                new Visitor("Jack Zhou", 21, "13923456789", "310101200404051234", "V888888888", "VIP")
        };
        // Add all visitors to the waiting queue
        for (Visitor v : part5Visitors) {
            jungleTrain.addVisitorToQueue(v);
        }
        // Print the waiting queue before running
        System.out.println("\n=== Waiting Queue BEFORE running one cycle ===");
        jungleTrain.printQueue();
        // Run one cycle
        System.out.println("\n=== Run one ride cycle ===");
        jungleTrain.runOneCycle();
        // Print the waiting queue after running one cycle (with 6 people remaining)
        System.out.println("\n=== Waiting Queue AFTER running one cycle ===");
        jungleTrain.printQueue();
        // Print the history record after running (add 4 people)
        System.out.println("\n=== Ride History AFTER running one cycle ===");
        jungleTrain.printRideHistory();

    }

    /**
     * Part 6: Demonstrate how to export the history of rides as a CSV file
     */
    public void partSix() {
        System.out.println("\n===== Part 6 Demonstration =====");
        // Create an operator
        Employee carouselOperator = new Employee(
                "Emma", 26, "13912345678", "110101199803156789",
                "FA90123", "Ride Operator", "Fairy Tale Area");
        // Create a carousel ride
        Ride exportRide = new Ride(
                "Carousel", "Fairy Tale Area", "Family Rides",
                carouselOperator);
        // Create 5 visitors and add them to history
        Visitor v1 = new Visitor("Emma Watson",7,"13912345678","110101201803156789", "O666655555", "Ordinary tourist");
        Visitor v2 = new Visitor("Liam Hemsworth",5,"13887654321","310101202007201234", "V777766666", "VIP");
        Visitor v3 = new Visitor("Olivia Rodrigo", 21, "13765432109", "44010120030905789X", "S888877777", "SVIP");
        Visitor v4 = new Visitor("Noah Centineo", 27, "13698765432", "510101199702103456", "O999988888", "Ordinary tourist");
        Visitor v5 = new Visitor("Ava Max", 30, "13578901234", "110101199405156789", "V000011111", "VIP");
        exportRide.addVisitorToHistory(v1);
        exportRide.addVisitorToHistory(v2);
        exportRide.addVisitorToHistory(v3);
        exportRide.addVisitorToHistory(v4);
        exportRide.addVisitorToHistory(v5);
        // The path of the exported file (replace the space in the facility name with an underscore)
        String filePath = exportRide.getRideName().replace(" ", "_") + "_History.csv";
        System.out.println("\n===Export ride history to csv file ===");
        exportRide.exportRideHistory(filePath);
    }

    /**
     * Part 7: Demonstration of Importing the history of amusement facilities from CSV files
     */
    public void partSeven() {
        System.out.println("\n===== Part 7 Demonstration =====");
        // Create an operator
        Employee importOperator = new Employee(
                "Emma", 26, "13912345678", "110101199803156789",
                "FA90123", "Ride Operator", "Fairy Tale Area");
        //Create a carousel facility for import
        Ride importRide = new Ride(
                "Imported Carousel", "Fairy Tale Area", "Family Rides",
                importOperator);
        //Import the CSV file exported in Part 6
        String filePath = "Carousel_History.csv";
        importRide.importRideHistory(filePath);
        // Print the history records after import
        System.out.println("\n=== The history of the ride after import ===");
        importRide.printRideHistory();

    }
}


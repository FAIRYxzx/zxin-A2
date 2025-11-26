public class AssignmentTwo {
    public static void main(String[] args) {
    }

    public void partThree() {
        System.out.println("\n===== Part 3 Demonstration =====");
        Ride rollerCoaster = new Ride();
        rollerCoaster.setRideName("Roller coaster");
        rollerCoaster.setRideParkArea("Adventure Zone");
        rollerCoaster.setRideType("Thrill Rides");

        Visitor v1 = new Visitor("Jack", 25, "13411122333", "11010119900101123X", "O111111111", "Ordinary tourist");
        Visitor v2 = new Visitor("Jerry", 21, "13511122333", "310101199505154566", "V111111111", "VIP");
        Visitor v3 = new Visitor("Tom", 22, "13611122333", "440106200010207899", "S111111111", "SVIP");
        Visitor v4 = new Visitor("Lucy", 18, "13711122333", "440304198508082464", "O222222222", "Ordinary tourist");
        Visitor v5 = new Visitor("Mark", 24, "13811122333", "510104200503033577", "V222222222", "VIP");


        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);
        System.out.println("=== The waiting queue after the addition ===");
        rollerCoaster.printQueue();

        rollerCoaster.removeVisitorFromQueue();

        System.out.println("\n=== The waiting queue of the first visitor after removal ===");
        rollerCoaster.printQueue();

    }

    public void partFourA() {
    }

    public void partFourB() {
    }

    public void partFive() {
    }

    public void partSix() {
    }

    public void partSeven() {
    }
}


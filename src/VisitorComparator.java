/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * The VisitorComparator class implements the Comparator<Visitor> interface
 * It is used to sort visitors by membership level (priority from high to low: SVIP > VIP > regular visitors) and age (in ascending order)
 */

import java.util.*;

public class VisitorComparator implements Comparator<Visitor> {
    private static final Map<String, Integer> MEMBERSHIP_PRIORITY = new HashMap<>();
    //Initialize member priority (the higher the value, the higher the priority)
    static {
        MEMBERSHIP_PRIORITY.put("SVIP", 3);
        MEMBERSHIP_PRIORITY.put("VIP", 2);
        MEMBERSHIP_PRIORITY.put("Ordinary tourist", 1);
    }

    /**
     * A method for comparing two visitor objects
     * @param v1 The first visitor object to be compared
     * @param v2 The second visitor object to be compared
     * @return Comparison result: A positive number indicates that the priority of v1 is lower than that of v2, a negative number indicates that the priority of v1 is higher than that of v2, and 0 indicates the same priority
     */
    public int compare(Visitor v1, Visitor v2) {
        //Handling empty objects: null is regarded as having the lowest priority
        if (v1 == null) return -1;
        if (v2 == null) return 1;
        //Get the membership priority of two tourists
        int priority1 = MEMBERSHIP_PRIORITY.get(v1.getMembershipType());
        int priority2 = MEMBERSHIP_PRIORITY.get(v2.getMembershipType());
        //First, sort by member priority (those with higher priority come first)
        if (priority1 != priority2) {
            return Integer.compare(priority2, priority1);
        }
        //When members have the same priority, they will be sorted in ascending order of age (with younger members at the top).
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}
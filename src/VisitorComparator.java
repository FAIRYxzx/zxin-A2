import java.util.*;

public class VisitorComparator implements Comparator<Visitor> {
    private static final Map<String, Integer> MEMBERSHIP_PRIORITY = new HashMap<>();
    static {
        MEMBERSHIP_PRIORITY.put("SVIP", 3);
        MEMBERSHIP_PRIORITY.put("VIP", 2);
        MEMBERSHIP_PRIORITY.put("Ordinary tourist", 1);
    }

    public int compare(Visitor v1, Visitor v2) {

        if (v1 == null) return -1;
        if (v2 == null) return 1;

        int priority1 = MEMBERSHIP_PRIORITY.get(v1.getMembershipType());
        int priority2 = MEMBERSHIP_PRIORITY.get(v2.getMembershipType());
        if (priority1 != priority2) {
            return Integer.compare(priority2, priority1);
        }
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}
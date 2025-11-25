import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Visitor extends Person {
    private static final Map<String, Character> MEMBERSHIP_ID_MAP = new HashMap<>();
    static {
        MEMBERSHIP_ID_MAP.put("Ordinary tourist", 'O');
        MEMBERSHIP_ID_MAP.put("VIP", 'V');
        MEMBERSHIP_ID_MAP.put("SVIP", 'S');
    }
    private static final Set<String> VALID_MEMBERSHIPS = MEMBERSHIP_ID_MAP.keySet();

    private static final Set<String> USED_VISITOR_IDS = new HashSet<>();

    private String visitorID;
    private String membershipType;

    public Visitor() {}


    public Visitor(String name, int age, String phone, String id,
                   String visitorID, String membershipType) {
        super(name, age, phone, id);
        this.setMembershipType(membershipType);
        this.setVisitorID(visitorID);
    }


    public void setVisitorID(String visitorID) {

        if (visitorID == null || visitorID.trim().isEmpty()) {
            System.out.println("Error: The visitor ID cannot be empty！");
            return;
        }
        if (visitorID.length() != 10) {
            System.out.println("Error: The visitor ID is invalid. Its length must be 8 digits！");
            return;
        }

        char firstChar = visitorID.charAt(0);
        if (!MEMBERSHIP_ID_MAP.containsValue(firstChar)) {
            System.out.println("Error: The visitor ID is invalid. The first digit must be O (Ordinary tourist), V (VIP), or S (SVIP)!");
            return;
        }

        String suffix = visitorID.substring(1);
        if (!suffix.matches("\\d{9}")) {
            System.out.println("Error: The visitor ID is invalid. The last 7 digits must be numbers!");
            return;
        }

        if (USED_VISITOR_IDS.contains(visitorID)) {
            System.out.println("Error: Duplicate visitorID！");
            return;
        }


        if (this.visitorID != null) {
            USED_VISITOR_IDS.remove(this.visitorID);
        }
        this.visitorID = visitorID;
        USED_VISITOR_IDS.add(visitorID);

        System.out.println("The visitor ID is set to：" + visitorID);
    }


    public void setMembershipType(String membershipType) {
        if (membershipType == null || !VALID_MEMBERSHIPS.contains(membershipType)) {
            System.out.println("Error: The membership type is invalid! Only supported：" + VALID_MEMBERSHIPS);
            return;
        }


        if (this.visitorID != null && !this.visitorID.trim().isEmpty()) {
            char requiredFirstChar = MEMBERSHIP_ID_MAP.get(membershipType);
            char actualFirstChar = this.visitorID.charAt(0);
            if (actualFirstChar != requiredFirstChar) {
                System.out.println("Error: The member type does not match the visitor ID！");
                return;
            }
        }

        this.membershipType = membershipType;
        System.out.println("The membership type is set to：" + membershipType);
    }


    private String getMembershipByFirstChar(char firstChar) {
        for (Map.Entry<String, Character> entry : MEMBERSHIP_ID_MAP.entrySet()) {
            if (entry.getValue().equals(firstChar)) {
                return entry.getKey();
            }
        }
        return "";
    }


    public String getVisitorID() {
        return visitorID;
    }

    public String getMembershipType() {
        return membershipType;
    }


    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Visitor ID: " + visitorID + "\n" +
                "Membership type: " + membershipType;
    }


    public static void clearUsedIds() {
        USED_VISITOR_IDS.clear();
        System.out.println("All used guest ids have been cleared");
    }
}
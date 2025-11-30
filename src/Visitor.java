/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * The visitor class inherits from the Person abstract class
 * It includes unique attributes for visitors, as well as related verification and setting methods
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Visitor extends Person {
    // Mapping table of member types to the first letters of visitor ids
    private static final Map<String, Character> MEMBERSHIP_ID_MAP = new HashMap<>();
    static {
        MEMBERSHIP_ID_MAP.put("Ordinary tourist", 'O');
        MEMBERSHIP_ID_MAP.put("VIP", 'V');
        MEMBERSHIP_ID_MAP.put("SVIP", 'S');
    }
    //A collection of valid member types (extracting keys from the mapping table)
    private static final Set<String> VALID_MEMBERSHIPS = MEMBERSHIP_ID_MAP.keySet();
    //The collection of used visitor ids (ensure that the visitor ids are unique)
    private static final Set<String> USED_VISITOR_IDS = new HashSet<>();

    private String visitorID; //Visitor ID (Format: 1 first letter of member type + 9 digits)
    private String membershipType; //Membership type (Ordinary tourist/VIP/SVIP)

    /**
     * Parameterless construction method
     */
    public Visitor() {
        super();
    }

    /**
     * There is a parameter constructor method to initialize the visitor information
     * @param name Name (inherited from Person)
     * @param age Age (inherited from Person
     * @param phone Telephone (inherited from Person
     * @param id Id number (inherited from Person)
     * @param visitorID Visitor ID
     * @param membershipType Membership type
     */
    public Visitor(String name, int age, String phone, String id,
                   String visitorID, String membershipType) {
        super(name, age, phone, id); //Call the constructor of the parent class to initialize personal information
        this.setMembershipType(membershipType); //Set the membership type (with verification)
        this.setVisitorID(visitorID); //Set the visitor ID (with verification)
    }

    /**
     * Set the visitor ID (including format verification)
     * Visitor ID rules: 10 digits in length, with the first letter being O/V/S (corresponding to the membership type), and the last 9 digits being numbers, and the ID is unique
     * @param visitorID The visitor ID to be set
     */
    public void setVisitorID(String visitorID) {
        //Verify that the visitor ID is not empty
        if (visitorID == null || visitorID.trim().isEmpty()) {
            System.out.println("Error: The visitor ID cannot be empty！");
            return;
        }
        //The verification length is 10 digits
        if (visitorID.length() != 10) {
            System.out.println("Error: The visitor ID is invalid. Its length must be 10 digits！");
            return;
        }
        //Verify that the first letter is a valid member type identifier (O/V/S)
        char firstChar = visitorID.charAt(0);
        if (!MEMBERSHIP_ID_MAP.containsValue(firstChar)) {
            System.out.println("Error: The visitor ID is invalid. The first digit must be O (Ordinary tourist), V (VIP), or S (SVIP)!");
            return;
        }
        //The 9 digits after verification are numbers
        String suffix = visitorID.substring(1);
        if (!suffix.matches("\\d{9}")) {  //Regular expressions match 9-digit numbers
            System.out.println("Error: The visitor ID is invalid. The last 9 digits must be numbers!");
            return;
        }
        //The verification ID is unique
        if (USED_VISITOR_IDS.contains(visitorID)) {
            System.out.println("Error: Duplicate visitorID！");
            return;
        }

        //If an old ID already exists, remove it from the used collection
        if (this.visitorID != null) {
            USED_VISITOR_IDS.remove(this.visitorID);
        }
        //Set a new ID and add it to the used collection
        this.visitorID = visitorID;
        USED_VISITOR_IDS.add(visitorID);

        System.out.println("The visitor ID for " + getName() + " is set to：" + visitorID);
    }

    /**
     * Set the member type (including verification, and it must match the first letter of the visitor ID)
     * @param membershipType The type of member to be set
     */
    public void setMembershipType(String membershipType) {
        //Verify whether the membership type is valid (it must be one of the preset three types)
        if (membershipType == null || !VALID_MEMBERSHIPS.contains(membershipType)) {
            System.out.println("Error: The membership type is invalid! Only supported：" + VALID_MEMBERSHIPS);
            return;
        }

        //If a visitor ID has been set, verify whether the member type matches the first letter of the ID
        if (this.visitorID != null && !this.visitorID.trim().isEmpty()) {
            char requiredFirstChar = MEMBERSHIP_ID_MAP.get(membershipType); //The first letter corresponding to the membership type
            char actualFirstChar = this.visitorID.charAt(0); //The first letter of the actual visitor ID
            if (actualFirstChar != requiredFirstChar) {
                System.out.println("Error: The member type does not match the visitor ID！");
                return;
            }
        }
        //Verification passed. Set the membership type
        this.membershipType = membershipType;
        System.out.println("The membership type for " + getName() + " is set to：" + membershipType);
    }

    /**
     * Obtain the corresponding member type based on the first letter of the ID (internal auxiliary method)
     * @param firstChar The first letter of the visitor ID
     * @return The corresponding member type (return an empty string if it does not exist)
     */
    private String getMembershipByFirstChar(char firstChar) {
        for (Map.Entry<String, Character> entry : MEMBERSHIP_ID_MAP.entrySet()) {
            if (entry.getValue().equals(firstChar)) {
                return entry.getKey();
            }
        }
        return "";
    }

    //Getter methods
    public String getVisitorID() {
        return visitorID;
    }

    public String getMembershipType() {
        return membershipType;
    }

    /**
     * Rewrite the toString method to return the complete information of the visitor
     * @return A string containing personal information, visitor ID, and member type
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Visitor ID: " + visitorID + "\n" +
                "Membership type: " + membershipType;
    }

    /**
     * Clear the collection of used visitor ids (for reset)
     */
    public static void clearUsedIds() {
        USED_VISITOR_IDS.clear();
        System.out.println("All used visitor ids have been cleared");
    }
}
/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * The employee class inherits from the Person abstract class
 * It includes employee-specific attributes, as well as related verification and setting methods
 */

import java.util.HashMap;
import java.util.Map;

public class Employee extends Person {
    //A mapping table of park abbreviations and park names
    private static final Map<String, String> AREA_ABBR_MAP = new HashMap<>();
    //Initialize the mapping of park abbreviations
    static {
        AREA_ABBR_MAP.put("AD", "Adventure Zone");
        AREA_ABBR_MAP.put("WP", "Water Park");
        AREA_ABBR_MAP.put("FA", "Fairy Tale Area");
        AREA_ABBR_MAP.put("SC", "Science Center");
    }
    private String employeeId; //Employee ID (Format: 2-digit abbreviation of the park + 5-digit number
    private String position; //Position (such as "Ride Operator")
    private String parkArea; //The affiliated park (it must be the complete name in the mapping table)

    /**
     * Parameterless constructor, initializing default values
     */
    public Employee() {
        super();
    }

    /**
     * There is a parameter constructor method to initialize the basic information of employees
     * @param name Name (inherited from Person)
     * @param age Age (inherited from Person）
     * @param phone Telephone (inherited from Person
     * @param id Id number (inherited from Person)
     * @param employeeId Employee ID
     * @param position Position
     * @param parkArea The affiliated park
     */
    public Employee(String name, int age, String phone, String id,
                    String employeeId, String position, String parkArea) {
        super(name, age, phone, id); //Call the constructor of the parent class to initialize personal information
        //Call the setter to verify and set the employee ID, position, and affiliated campus
        this.setEmployeeId(employeeId);
        this.setPosition(position);
        this.setParkArea(parkArea);
    }

    /**
     * Set the employee ID (including format verification)
     * Employee ID rules: The length is 7 digits. The first 2 digits are the abbreviation of the park (AD/WP/FA/SC), and the last 5 digits are numbers, which must match the affiliated park
     * @param employeeId The employee ID to be set
     */
    public void setEmployeeId(String employeeId) {
        //The verification ID is not empty
        if (employeeId == null || employeeId.trim().isEmpty()) {
            System.out.println("Error: Employee ID is invalid. Cannot be empty！");
            return;
        }
        //The verification length is 7 bits
        if (employeeId.length() != 7) {
            System.out.println("Error: Employee ID is invalid. The length must be 7 digits (current length:" + employeeId.length() + "）！");
            return;
        }
        //Extract the first two abbreviations and verify whether it is a valid park
        String abbr = employeeId.substring(0, 2).toUpperCase();
        if (!AREA_ABBR_MAP.containsKey(abbr)) {
            System.out.println("Error: Employee ID is invalid. The first two letters of the ID" + abbr + "are not valid park abbreviations (Valid abbreviations: AD/WP/FA/SC)！");
            return;
        }

        //Extract the last 5 digits and verify if they are numbers
        String suffix = employeeId.substring(2);
        if (!suffix.matches("\\d{5}")) { //Regular expressions match 5-digit numbers
            System.out.println("Error: Employee ID is invalid.The last five digits must be numbers！");
            return;
        }

        //Verify whether the employee ID matches the affiliated park
        if (this.parkArea != null && !this.parkArea.trim().isEmpty()) {
            String matchedArea = AREA_ABBR_MAP.get(abbr);
            if (!matchedArea.equals(this.parkArea)) {
                System.out.println("Error: The employee ID does not match the affiliated park！");
                return;
            }
        }

        //All verifications have been passed. Set the employee ID
        this.employeeId = employeeId;
        System.out.println("The employee ID is set to：" + employeeId);
    }

    /**
     * Set up the affiliated park (including verification
     * The affiliated campus must be a valid name in the mapping table and must match the abbreviation of the campus in the employee ID
     * @param parkArea The name of the park to be set up
     */
    public void setParkArea(String parkArea) {
        //Verify that the park is not empty
        if (parkArea == null || parkArea.trim().isEmpty()) {
            System.out.println("Error: The affiliated park cannot be empty!");
            return;
        }
        //Verify whether the park is a valid name
        if (!AREA_ABBR_MAP.containsValue(parkArea)) {
            System.out.println("Error: The affiliated park does not exist or is not a valid park！");
            return;
        }
        //Verify whether the park ID matches the employee ID
        if (this.employeeId != null && !this.employeeId.trim().isEmpty()) {
            String abbr = this.employeeId.substring(0, 2).toUpperCase();  //Extract the abbreviation from the ID
            String matchedArea = AREA_ABBR_MAP.get(abbr);
            if (!matchedArea.equals(parkArea)) {
                System.out.println("Error: The employee ID does not match the affiliated park！");
                return;
            }
        }
        //All verifications are successful. Set the affiliated park
        this.parkArea = parkArea;
        System.out.println("The affiliated park is set to: " + parkArea);
    }

    /**
     * Obtain the corresponding abbreviation based on the name of the park
     * @param parkArea Name of the Park
     * @return The corresponding abbreviation (return an empty string if not present)
     */
    private String getAbbrByArea(String parkArea) {
        for (Map.Entry<String, String> entry : AREA_ABBR_MAP.entrySet()) {
            if (entry.getValue().equals(parkArea)) {
                return entry.getKey();
            }
        }
        return "";
    }

    //Getter and Setter methods (property access and setting)
    public String getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }

    /**
     * Set positions (non-empty verification)
     * @param position The position to be set
     */
    public void setPosition(String position) {
        if (position != null && !position.trim().isEmpty()) {
            this.position = position;
        } else {
            System.out.println("Error: The position cannot be vacant！");
        }
    }

    public String getParkArea() {
        return parkArea;
    }

    /**
     * Rewrite the toString method to return the complete information of the employee
     * @return A string containing personal information, employee ID, position, and the affiliated campus
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Employee ID: " + employeeId + "\n" +
                "Position: " + position + "\n" +
                "Affiliated park: " + parkArea;
    }
}
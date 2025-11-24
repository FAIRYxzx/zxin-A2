import java.util.HashMap;
import java.util.Map;

public class Employee extends Person {
    private static final Map<String, String> AREA_ABBR_MAP = new HashMap<>();
    static {
        AREA_ABBR_MAP.put("AD", "Adventure Zone");
        AREA_ABBR_MAP.put("WP", "Water Park");
        AREA_ABBR_MAP.put("FA", "Fairy Tale Area");
        AREA_ABBR_MAP.put("SC", "Science Center");
    }
    private String employeeId;
    private String position;
    private String parkArea;

    public Employee() {}

    public Employee(String name, int age, String phone, String id,
                    String employeeId, String position, String parkArea) {
        super(name, age, phone, id);

        this.setEmployeeId(employeeId);
        this.setPosition(position);
        this.setParkArea(parkArea);
    }


    public void setEmployeeId(String employeeId) {

        if (employeeId == null || employeeId.trim().isEmpty()) {
            System.out.println("Error: Employee ID is invalid. Cannot be empty！");
            return;
        }
        if (employeeId.length() != 7) {
            System.out.println("Error: Employee ID is invalid. The length must be 7 digits (current length:" + employeeId.length() + "）！");
            return;
        }

        String abbr = employeeId.substring(0, 2).toUpperCase();
        if (!AREA_ABBR_MAP.containsKey(abbr)) {
            System.out.println("Error: Employee ID is invalid. The first two letters of the ID" + abbr + "are not valid park abbreviations (Valid abbreviations: AD/WP/FA/SC)！");
            return;
        }


        String suffix = employeeId.substring(2);
        if (!suffix.matches("\\d{5}")) {
            System.out.println("Error: Employee ID is invalid.The last five digits must be numbers！");
            return;
        }

        if (this.parkArea != null && !this.parkArea.trim().isEmpty()) {
            String matchedArea = AREA_ABBR_MAP.get(abbr);
            if (!matchedArea.equals(this.parkArea)) {
                System.out.println("Error: The employee ID does not match the affiliated park！");
                return;
            }
        }

        this.employeeId = employeeId;
        System.out.println("The employee ID is set to：" + employeeId);
    }


    public void setParkArea(String parkArea) {
        if (parkArea == null || parkArea.trim().isEmpty()) {
            System.out.println("Error: The affiliated park cannot be empty!");
            return;
        }
        if (!AREA_ABBR_MAP.containsValue(parkArea)) {
            System.out.println("Error: The affiliated park does not exist or is not a valid park！");
            return;
        }

        if (this.employeeId != null && !this.employeeId.trim().isEmpty()) {
            String abbr = this.employeeId.substring(0, 2).toUpperCase();
            String matchedArea = AREA_ABBR_MAP.get(abbr);
            if (!matchedArea.equals(parkArea)) {
                System.out.println("Error: The employee ID does not match the affiliated park！");
                return;
            }
        }

        this.parkArea = parkArea;
        System.out.println("The affiliated park is set to: " + parkArea);
    }


    private String getAbbrByArea(String parkArea) {
        for (Map.Entry<String, String> entry : AREA_ABBR_MAP.entrySet()) {
            if (entry.getValue().equals(parkArea)) {
                return entry.getKey();
            }
        }
        return "";
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }

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

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Employee ID: " + employeeId + "\n" +
                "Position: " + position + "\n" +
                "Affiliated park: " + parkArea;
    }
}
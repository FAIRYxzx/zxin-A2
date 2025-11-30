/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * The abstract Person class serves as the parent class of Employee and Visitor
 * It includes the common attributes of all personnel: name, age, phone number, ID number, as well as the related access and setter methods
 */
public abstract class Person {
    private String name;  //Name
    private int age;  //Age (0-70 years old)
    private String phone; //Mobile phone number (11 significant digits)
    private String id;  //ID number (18 digits, the last digit can be X/x.

    /**
     * Parameterless construction method
     */
    public Person() {}

    /**
     * There is a parameter constructor method to initialize the basic information of the personnel
     * @param name Name
     * @param age Age
     * @param phone Telephone
     * @param id ID number
     */
    public Person(String name, int age, String phone, String id) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.id = id;
    }

    // Getter and Setter methods (including basic validation)
    public String getName() {
        return name;
    }

    /**
     * Set the name (non-empty verification)
     * @param Name The name to be set
     */
    public void setName(String Name) {
        if (Name != null && !Name.trim().isEmpty()) {
            this.name = Name;
        } else {
            System.out.println("Error: The name cannot be empty");
        }
    }

    public int getAge() {
        return age;
    }

    /**
     * Set the age (range verification: 0-70 years old)
     * @param age The age to be set
     */
    public void setAge(int age) {
        if (age >= 0 && age <= 70) {
            this.age = age;
        } else {
            System.out.println("Error: The age must be between 0 and 70");
        }
    }

    public String getPhone() {
        return phone;
    }

    /**
     * Set the mobile phone number (format verification: 11 digits, starting with 1, the second digit is 3-9, and the last 9 digits)
     * @param phone The mobile phone number to be set
     */
    public void setPhone(String phone) {

        if (phone != null && phone.matches("^1[3-9]\\d{9}$")) {  //Regular matching: starting with 1, the second digit is 3 to 9, and the last 9 digits (a total of 11 digits)
            this.phone = phone;
        } else {
            System.out.println("Error: The mobile phone number should be 11 significant digits");
        }
    }

    public String getID() {
        return id;
    }

    /**
     * Set the ID number (format verification: 18 digits, the last digit can be X/x)
     * @param id The ID number to be set
     */
    public void setID(String id) {
        if (id != null && id.matches("^\\d{17}[0-9Xx]$")) {  // Regular matching: 17 digits + last digit or X/x
            this.id = id;
        } else {
            System.out.println("Error: The format of the ID number is incorrect (it should be 18 digits, and the last digit can be X).");
        }
    }

    /**
     * Rewrite the toString method to return the basic information of the personnel
     * @return A string containing name, age, phone number and ID number
     */
    @Override
    public String toString() {
        return "Name " + name + "\n" +
                "Age: " + age + "\n" +
                "Phone: " + phone + "\n" +
                "ID: " + id;
    }
}

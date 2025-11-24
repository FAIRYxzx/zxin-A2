public abstract class Person {
    private String name;
    private int age;
    private String phone;
    private String id;


    public Person() {}

    public Person(String name, int age, String phone, String id) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.id = id;
    }


    public String getName() {
        return name;
    }

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

    public void setPhone(String phone) {

        if (phone != null && phone.matches("^1[3-9]\\d{9}$")) {
            this.phone = phone;
        } else {
            System.out.println("Error: The mobile phone number should be 11 significant digits");
        }
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        if (id != null && id.matches("^\\d{17}[0-9Xx]$")) {
            this.id = id;
        } else {
            System.out.println("Error: The format of the ID number is incorrect (it should be 18 digits, and the last digit can be X).");
        }
    }

    @Override
    public String toString() {
        return "Name " + name + "\n" +
                "Age: " + age + "\n" +
                "Phone: " + phone + "\n" +
                "ID: " + id;
    }
}

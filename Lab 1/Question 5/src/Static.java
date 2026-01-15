
public final class Static {

    // 1. static variable (belongs to class, not objects)
    public static int studentCount = 0;

    // 2. final variable (constant - value can't be changed)
    public static final String COLLEGE_NAME = "XYZ Institute of Technology";
    public final double PI = 3.1415926535;      // instance final variable

    // Normal instance variable
    private String name;

    // Constructor
    public Static(String name) {
        this.name = name;
        studentCount++;           // increment static counter
    }

    // 3. static method (can be called without creating object)
    public static void printCollegeInfo() {
        System.out.println("College: " + COLLEGE_NAME);
        System.out.println("Total students registered: " + studentCount);
        // System.out.println(name);  ← ERROR! static method can't access non-static
    }

    // 4. final method (cannot be overridden in child class)
    public final void displayStudentInfo() {
        System.out.println("Name: " + name);
        System.out.println("College: " + COLLEGE_NAME);
        System.out.println("PI value: " + PI);
    }

    // 5. static block (runs only once when class is loaded)
    static {
        System.out.println("==");
        System.out.println("Static block executed - Class is loading...");
        System.out.println("Welcome to " + COLLEGE_NAME);
        System.out.println("==\n");
    }

    public static void main(String[] args) {
        System.out.println("Main method started\n");

        // Calling static method without object
        Static.printCollegeInfo();
        // Creating objects
        Static s1 = new Static("Aarav");
        Static s2 = new Static("Priya");
        Static s3 = new Static("Rahul");

        System.out.println("\nAfter creating 3 students:");
        // Static method call again - updated count
        Static.printCollegeInfo();

        System.out.println("\nIndividual student info:");
        s1.displayStudentInfo();

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
        // s1.PI = 3.14;  ← ERROR! final variable can't be reassigned

        // COLLEGE_NAME = "ABC";  ← ERROR! final static variable
    }
}
public class StudentConstructorOverloading {

    private String name;
    private int rollNo;
    private double marks;

    // 1. Default constructor
    public StudentConstructorOverloading() {
        this("Unknown", 0, 0.0);
    }

    // 2. Name only
    public StudentConstructorOverloading(String name) {
        this(name, 101, 75.0);
    }

    // 3. Name + Roll no
    public StudentConstructorOverloading(String name, int rollNo) {
        this(name, rollNo, 82.5);
    }

    // 4. Full constructor (main one)
    public StudentConstructorOverloading(String name, int rollNo, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
        System.out.println("Constructor called: " + name);
    }

    public void show() {
        System.out.printf("%-12s | Roll: %3d | Marks: %.1f%%\n",
                name, rollNo, marks);
    }

    public static void main(String[] args) {
        StudentConstructorOverloading s1 = new StudentConstructorOverloading();
        StudentConstructorOverloading s2 = new StudentConstructorOverloading("Rahul");
        StudentConstructorOverloading s3 = new StudentConstructorOverloading("Priya", 42);
        StudentConstructorOverloading s4 = new StudentConstructorOverloading("Aman", 15, 94.8);

        System.out.println("\nStudent List:");
        s1.show();
        s2.show();
        s3.show();
        s4.show();
        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
    }
}
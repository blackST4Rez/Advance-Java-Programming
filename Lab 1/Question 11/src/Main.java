
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the length of the Rectangle : ");
        double length = sc.nextDouble();

        System.out.print("Enter the breadth of the Rectangle : ");
        double breadth = sc.nextDouble();

        // Using polymorphism - we can also create other shapes later
        Shape rectangle = new Rectangle();

        System.out.println("The area is: " + rectangle.area(length, breadth));
        System.out.println("The perimeter is: " + rectangle.perimeter(length, breadth));

        sc.close();

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

    }
}
import java.util.Scanner;           // ← only this one class
import java.time.LocalDateTime;     // ← only this one class

public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your birth year: ");
        int year = sc.nextInt();

        LocalDateTime now = LocalDateTime.now();
        int age = now.getYear() - year;

        System.out.println("You are approximately " + age + " years old!");

        sc.close();

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
    }
}
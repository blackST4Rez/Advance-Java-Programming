import java.util.*;          // ← imports ALL classes from java.util
import java.time.*;         // ← imports ALL classes from java.time

public class Main3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        LocalDate today = LocalDate.now();

        System.out.print("How many lucky numbers do you want? ");
        int count = scanner.nextInt();

        System.out.println("Your lucky numbers for " + today + " are:");
        for (int i = 0; i < count; i++) {
            System.out.print(random.nextInt(100) + " ");
        }
        System.out.println();

        scanner.close();

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
    }
}
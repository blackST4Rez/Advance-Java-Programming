public class Main1 {
    public static void main(String[] args) {

        // Using fully qualified name - no import needed
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Hello " + name + "!");

        scanner.close();

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");

    }
}
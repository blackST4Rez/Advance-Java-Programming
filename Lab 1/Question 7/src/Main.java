
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Creating GrandChild object ===\n");


        GrandChild gc = new GrandChild(100, 200);

        System.out.println("\n=== Displaying numbers from GrandChild ===");
        gc.displayNums();

        System.out.println("\n=== Displaying numbers from Child level ===");

        ((Child) gc).displayNums();

        System.out.println("\nParent's final num (accessed via Child cast): " +
                ((Parent) gc).num);

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
    }
}
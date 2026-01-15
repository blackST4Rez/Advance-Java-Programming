public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.setCarName("Tesla Model 3");
        myCar.charge();
        myCar.drive();

        System.out.println("=====");

        myCar.setCarName("Hyundai Ioniq 5");
        myCar.charge();
        myCar.drive();

        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
    }
}
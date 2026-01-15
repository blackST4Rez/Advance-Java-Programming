public class CalcMethodOverloading {

    // 1. Add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // 2. Add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // 3. Add two doubles
    public double add(double a, double b) {
        return a + b;
    }

    // 4. Add string (concatenation - different meaning)
    public String add(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) {
        CalcMethodOverloading calc = new CalcMethodOverloading();

        System.out.println("10 + 20 = " + calc.add(10, 20));
        System.out.println("5 + 15 + 25 = " + calc.add(5, 15, 25));
        System.out.println("3.5 + 4.7 = " + calc.add(3.5, 4.7));
        System.out.println("\"Hello\" + \"World\" = " + calc.add("Hello", "World"));
        System.out.println();
        System.out.println("Lab No : 1");
        System.out.println("Name : Raka Maharjan");
        System.out.println("ID : 2308-1002");
    }
}
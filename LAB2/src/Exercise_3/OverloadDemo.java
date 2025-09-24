package Exercise_3;

public class OverloadDemo {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println("Add two ints: " + add(4, 9));
        System.out.println("Add three ints: " + add(9, 2, 6));
        System.out.println("Add two doubles: " + add(3.6, 2.1));
    }
}

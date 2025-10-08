package exercise2;

public class PartTimeGameTester extends GameTester {
    private int hours;
    private static final double HOURLY_RATE = 20.0;

    public PartTimeGameTester(String name, int hours) {
        super(name, false);
        this.hours = hours;
    }

    @Override
    public double calculateSalary() {
        return hours * HOURLY_RATE;
    }
}

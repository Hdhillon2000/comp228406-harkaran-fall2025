package exercise2;

public abstract class GameTester {
    protected String name;
    protected boolean fullTime;

    public GameTester(String name, boolean fullTime) {
        this.name = name;
        this.fullTime = fullTime;
    }

    public String getName() {
        return name;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public abstract double calculateSalary();

    public void displayInfo() {
        String type = fullTime ? "Full-time" : "Part-time";
        System.out.printf("Name: %s | Type: %s | Salary: $%.2f%n", name, type, calculateSalary());
    }
}

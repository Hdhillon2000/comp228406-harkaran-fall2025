package exercise1;

public class Player {
    private int playerId;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private Integer age;

    public Player() {}

    public Player(int playerId, String firstName, String lastName, String email, String country, Integer age) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.age = age;
    }

    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    @Override
    public String toString() {
        if (firstName == null && lastName == null) return "Player " + playerId;
        return String.format("%s %s (id:%d)", firstName==null?"":firstName, lastName==null?"":lastName, playerId);
    }
}

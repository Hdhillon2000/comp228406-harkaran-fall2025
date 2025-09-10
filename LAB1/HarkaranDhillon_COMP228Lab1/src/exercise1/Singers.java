package exercise1;

public class Singers {

    private int id;
    private String name;
    private String address;
    private String dateOfBirth;
    private int numberOfAlbumsPublished;


    public Singers() { }


    public Singers(int id, String name, String address, String dateOfBirth, int numberOfAlbumsPublished) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }


    public void setAll(int id, String name, String address, String dateOfBirth, int numberOfAlbumsPublished) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setNumberOfAlbumsPublished(int numberOfAlbumsPublished) { this.numberOfAlbumsPublished = numberOfAlbumsPublished; }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDateOfBirth() { return dateOfBirth; }
    public int getNumberOfAlbumsPublished() { return numberOfAlbumsPublished; }
}

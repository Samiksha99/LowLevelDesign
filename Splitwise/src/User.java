public class User {
    private int id;
    private String name;
    private int phoneNo;
    public User(int id, String name, int phoneNo) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNo() {
        return phoneNo;
    }
}

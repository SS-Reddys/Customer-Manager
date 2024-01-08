package Lab3;

public class Customer {
	private static int idCounter = 1;
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    
    public Customer(int id, String name, String email, String phoneNumber) {
        this.id = idCounter++;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber;
    }
}

package models;

public class Admin extends User {

    public Admin(int id, String email) {
        super(id, email, "Admin", "User");
    }
}

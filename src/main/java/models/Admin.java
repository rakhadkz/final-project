package models;

public class Admin extends api.models.User {

    public Admin(String email) {
        super(-1, email, "Admin", "User");
    }

    public Admin(int id, String email) {
        super(id, email, "User", "Admin");
    }
}

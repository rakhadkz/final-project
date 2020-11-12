package interfaces;

import models.Admin;
import models.Student;

import java.sql.SQLException;

public interface IAuth {
    Student authenticate(String email, String password) throws Exception;
    Admin admin_authenticate(String email, String password) throws Exception;
    void logOut();
}

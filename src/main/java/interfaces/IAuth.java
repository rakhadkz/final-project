package interfaces;

import java.sql.SQLException;

public interface IAuth {
    void authenticate(String email, String password) throws Exception;
    void admin_authenticate(String email, String password) throws Exception;
    void logOut();
}

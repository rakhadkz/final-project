package services;

import interfaces.IAuth;

import java.sql.*;

public class AuthService extends BasicService implements IAuth {

    public AuthService(Connection connection) {
        super(connection);
    }

    @Override
    public void authenticate(String email, String password) throws Exception {
        String query = "SELECT * FROM student WHERE email = ? AND password = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        closeAll();
        if (!resultSet.isBeforeFirst())
            throw new Exception("Incorrect email or password");
    }

    @Override
    public void admin_authenticate(String email, String password) throws Exception {
        String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        closeAll();
        if (!resultSet.isBeforeFirst())
            throw new Exception("Incorrect email or password");
    }

    @Override
    public void logOut() {

    }
}

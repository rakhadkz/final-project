package services;

import config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicService {
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    public BasicService() {
        this.connection = Database.getConnection();
    }

    public void closeAll() throws SQLException {
        this.connection.close();
    }

}

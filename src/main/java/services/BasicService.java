package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicService {
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    public BasicService(Connection connection) {
        this.connection = connection;
    }

    public void closeAll() throws SQLException {
        this.preparedStatement.close();
        this.statement.close();
        this.connection.close();
    }

}

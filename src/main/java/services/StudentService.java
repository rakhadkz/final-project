package services;

import interfaces.IStudent;
import models.Group;
import models.Major;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentService extends BasicService implements IStudent {

    public StudentService(Connection connection) {
        super(connection);
    }

    @Override
    public Student read(String email) throws Exception {
        String query = "SELECT * FROM student WHERE email = '" + email + "'";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        closeAll();
        if (!resultSet.isBeforeFirst()){
            throw new Exception("Student not found");
        }
        resultSet.next();
        return new Student(
                resultSet.getString("email"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                new Group(resultSet.getString("group")),
                new Major(resultSet.getString("major")),
                resultSet.getInt("year")
        );
    }

    @Override
    public void update(Student updated) throws Exception {

    }

    @Override
    public void delete(String email) throws Exception {

    }
}

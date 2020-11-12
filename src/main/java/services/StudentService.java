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
        super();
    }

    @Override
    public Student read(String email) throws Exception {
        String query = "select * from student where email = '" + email + "'";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        closeAll();
        if (!resultSet.isBeforeFirst()){
            throw new Exception("Student not found");
        }
        resultSet.next();
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                new Group(resultSet.getInt("group_id"), resultSet.getString("group_name")),
                new Major(resultSet.getInt("major_id"), resultSet.getString("major_name")),
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

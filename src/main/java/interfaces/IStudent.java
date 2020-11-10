package interfaces;

import models.Student;

public interface IStudent {
    //CRUD
    Student read(String email) throws Exception;
    void update(Student updated) throws Exception;
    void delete(String email) throws Exception;
}

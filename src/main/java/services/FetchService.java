package services;

import interfaces.IFetch;
import models.*;

import java.sql.ResultSet;
import java.util.ArrayList;

public class FetchService extends BasicService implements IFetch {

    @Override
    public ArrayList<Student> fetchStudents(int group_id, int major_id, int year, String name, String email) throws Exception {
        String query;
        if (group_id == 0 && major_id == 0 && year == 0 && name == null && email == null)
            query = "select *, g.name as group_name, m.name as major_name from student s " +
                    "inner join _group g " +
                    "on s.group_id = g.id " +
                    "inner join major m " +
                    "on s.major_id = m.id ";
        else
            query = "select *, g.name as group_name, m.name as major_name from student s " +
                    "inner join _group g " +
                    "on s.group_id = g.id " +
                    "inner join major m " +
                    "on s.major_id = m.id " +
                    "where s.major_id = ifnull(" + major_id + ", s.major_id) or " +
                    "s.group_id = ifnull(" + group_id + ", s.group_id) or " +
                    "s.year = ifnull(" + year + ", s.year) or " +
                    "concat(firstName, ' ', lastName) LIKE  ifnull('%" + name + "%', '%%') or " +
                    "email like ifnull('%" + email + "%', '%%')";

        ArrayList<Student> list = new ArrayList<>();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
            list.add(new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    new Group(resultSet.getInt("group_id"), resultSet.getString("group_name")),
                    new Major(resultSet.getInt("major_id"), resultSet.getString("major_name")),
                    resultSet.getInt("year")
            ));
        closeAll();
        return list;
    }

    @Override
    public ArrayList<Club> fetchClubs() throws Exception {
        ArrayList<Club> list = new ArrayList<>();
        String query = "select * from club";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
            list.add(new Club(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("image"),
                    resultSet.getDate("created_at")
            ));
        }
        closeAll();
        return list;
    }

    @Override
    public ArrayList<Event> fetchEvents(int major_id, int club_id) throws Exception {
        String query;
        if (major_id == 0 && club_id == 0){
            query = "select *, m.name as major_name, c.name as club_name from event e inner join club c " +
                    "on e.club_id = c.id " +
                    "inner join major m " +
                    "on e.major_id = m.id";
        }else{
            query = "select *, m.name as major_name, c.name as club_name from event e inner join club c " +
                    "on e.club_id = c.id " +
                    "inner join major m " +
                    "on e.major_id = m.id " +
                    "where e.club_id = " + club_id + " or e.major_id = " + major_id;
        }
        ArrayList<Event> list = new ArrayList<>();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
            list.add(new Event(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("image"),
                    resultSet.getDate("created_at"),
                    new Major(resultSet.getInt("major_id"), resultSet.getString("major_name")),
                    new Club(resultSet.getInt("club_id"), resultSet.getString("club_name"))
            ));
        closeAll();
        return list;
    }

    @Override
    public ArrayList<News> fetchNews(int major_id) throws Exception {
        String query;
        if (major_id == 0){
            query = "select *, m.name as major_name from news n inner join major m on n.major_id = m.id";
        }else{
            query = "select *, m.name as major_name from news n inner join major m " +
                    "on n.major_id = m.id where n.major_id = " + major_id;
        }
        ArrayList<News> list = new ArrayList<>();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next())
            list.add(new News(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("image"),
                    new Major(resultSet.getInt("major_id"), resultSet.getString("major_name")),
                    resultSet.getDate("created_at")
            ));
        closeAll();
        return list;
    }

    @Override
    public ArrayList<Student> fetchStudentsOfClub(int id) throws Exception {
        ArrayList<Student> list = new ArrayList<>();
        String query = "select *, m.name as major_name from students_clubs c " +
                "inner join student s on c.student_id = s.id " +
                "inner join _group g on s.group_id = g.id " +
                "inner join major m on s.major_id = m.id " +
                "where c.club_id = " + id;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next())
            list.add(new Student(
                    resultSet.getInt("student_id"),
                    resultSet.getString("email"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    new Group(resultSet.getInt("group_id"), resultSet.getString("name")),
                    new Major(resultSet.getInt("major_id"), resultSet.getString("major_name")),
                    resultSet.getInt("year")
            ));
        closeAll();
        return list;
    }
}

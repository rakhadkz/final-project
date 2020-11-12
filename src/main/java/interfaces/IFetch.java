package interfaces;

import models.Club;
import models.Event;
import models.News;
import models.Student;

import java.util.ArrayList;

public interface IFetch {
    ArrayList<Student> fetchStudents(int group_id, int major_id, int year, String name, String email) throws Exception;
    ArrayList<Club> fetchClubs() throws Exception;
    ArrayList<Event> fetchEvents(int major_id, int club_id) throws Exception;
    ArrayList<News> fetchNews(int major_id) throws Exception;
    ArrayList<Student> fetchStudentsOfClub(int id) throws Exception;
}

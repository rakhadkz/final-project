package models;

public class Student extends User {
    private Group group;
    private Major major;
    private int year;

    public Student() {
    }

    public Student(int id, String email, String firstName, String lastName, Group group, Major major, int year) {
        super(id, email, firstName, lastName);
        this.group = group;
        this.major = major;
        this.year = year;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

package models;

import java.util.Date;

public class News {
    private int id;
    private String name;
    private String description;
    private String image;
    private Major major;
    private Date created_at;

    public News(int id, String name, String description, String image, Major major, Date created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.major = major;
        this.created_at = created_at;
        this.image = image;
    }

    public News(int id, String name, String description, String image, Date created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}

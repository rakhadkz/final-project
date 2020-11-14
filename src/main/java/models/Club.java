package models;

import java.util.Date;

public class Club {
    private int id;
    private String name;
    private String description;
    private String image;
    private Date created_at;

    public Club() {

    }

    public Club(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Club(int id) {
        this.id = id;
    }

    public Club(int id, String name, String description, String image, Date created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.created_at = created_at;
    }

    public Club(String name, String description, String image) {
        this.name = name;
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}

package com.example.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="location")
public class Location {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="location")
    @NotEmpty(message = "location should be not empty")
    @Pattern(regexp = "[\\s]*[a-zA-Z0-9,\\#]+[\\s]*[a-zA-Z0-9.\\-\\,\\#]+[a-zA-Z0-9\\s.\\-\\,\\#]*$", message = "Ex: St # 123, North AVE New York. NY 12345")
    private String location;

    @ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName = "id")
    private Person owner;

    public Location(){
    }
    public Location(String location){
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

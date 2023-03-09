package com.example.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Table(name="Person")
@Entity
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Email
    @Column(name="email")
    private String email;
    @NotEmpty
    @Column(name="name")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @OneToMany(mappedBy = "owner")
    private List<Location> locationList;

    @Column(name="created_at")
    private LocalDate created_at;

    public Person(){
    }
    public Person(String name, String email){
        this.name = name;
        this.email = email;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}

package com.example.DTO;

import com.example.models.Person;
import com.example.models.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLocationDTO {
    @NotEmpty(message = "Address should be not empty")
    private String address;
    @NotNull(message = "You must add owner")
    private Person owner;
    @NotEmpty(message = "Set your role!")
    private UserRole role;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

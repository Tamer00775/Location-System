package com.example.DTO;

import com.example.models.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LocationDTO {
    @NotEmpty(message = "location should be not empty")
    @Pattern(regexp = "^(\\d+\\s)?(\\w+\\s)+(\\w+\\.)?\\s*\\b(\\w+)\\b[,]?\\s*(\\w{2})\\s*(\\d{5})(?:[-\\s]*(\\d{4}))?$", message = "Example: 123 Main St Anytown NY 12345 or 123 Main St., Anytown, NY 12345")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

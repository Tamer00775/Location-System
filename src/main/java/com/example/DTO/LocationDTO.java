package com.example.DTO;

import com.example.models.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LocationDTO {
    @NotEmpty(message = "location should be not empty")
    @Pattern(regexp = "[\\s]*[a-zA-Z0-9,\\#]+[\\s]*[a-zA-Z0-9.\\-\\,\\#]+[a-zA-Z0-9\\s.\\-\\,\\#]*$", message = "Ex: St # 123, North AVE New York. NY 12345")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

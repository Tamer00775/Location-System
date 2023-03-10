package com.example.repository;

import com.example.models.Location;
import com.example.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository  extends JpaRepository<Location, Integer> {

}

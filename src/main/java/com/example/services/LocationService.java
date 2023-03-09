package com.example.services;

import com.example.models.Location;
import com.example.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LocationService {
    private final LocationRepository locationRepository;
    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public List<Location> findAll(){
        return locationRepository.findAll();
    }
    @Transactional
    public void save(Location location){
        locationRepository.save(location);
    }
}

package com.example.services;

import com.example.models.Location;
import com.example.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
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

    public List<Location> findAvailable(){
        List<Location> locationList = locationRepository.findAll();
        System.out.println(locationList);
        for(int i =0 ; i < locationList.size(); i++){
            if(locationList.get(i).getOwner() != null)
                locationList.remove(locationList.get(i));
        }
        return locationList;
    }
}

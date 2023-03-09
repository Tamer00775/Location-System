package com.example.controllers;

import com.example.DTO.LocationDTO;
import com.example.DTO.PersonDTO;
import com.example.models.Location;
import com.example.models.Person;
import com.example.services.LocationService;
import com.example.util.LocationErrorResponse;
import com.example.util.LocationNotCreateException;
import com.example.util.PersonErrorResponse;
import com.example.util.PersonNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;
    private final ModelMapper modelMapper;
    @Autowired
    public LocationController(LocationService locationService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Location> index(){
        return locationService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid LocationDTO locationDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> errors  = bindingResult.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error: errors) {
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new LocationNotCreateException(errorMessage.toString());
        }
        locationService.save(convertToLocation(locationDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<LocationErrorResponse> handleException(LocationNotCreateException locationNotCreateException){
        LocationErrorResponse response = new LocationErrorResponse(
                locationNotCreateException.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // not found - 404
    }

    public LocationDTO convertToLocationDTO(Location location){
        return modelMapper.map(location, LocationDTO.class);
    }
    public Location convertToLocation(LocationDTO locationDTO){
        return modelMapper.map(locationDTO, Location.class);
    }
}

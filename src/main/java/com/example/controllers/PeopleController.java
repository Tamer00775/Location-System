package com.example.controllers;


import com.example.DTO.LocationDTO;
import com.example.DTO.PersonDTO;
import com.example.models.Location;
import com.example.models.Person;
import com.example.util.PersonErrorResponse;
import com.example.util.PersonNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.example.services.PeopleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class PeopleController {
    private final PeopleService peopleService;
    private final ModelMapper mapper;
    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper mapper) {
        this.peopleService = peopleService;

        this.mapper = mapper;
    }
    @GetMapping
    public List<Person> accountList(){
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable int id){
        return peopleService.findOne(id);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors  = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new PersonNotCreatedException(errorMessage.toString());
        }

        peopleService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/{id}/locations")
    public List<Location> locationList(@PathVariable int id){
        Person person = peopleService.findOne(id);
        System.out.println(person.getLocationList());
        return person.getLocationList();
    }
    @PostMapping("/{id}/create")
    public ResponseEntity<HttpStatus> createYourLocation(@PathVariable("id") int id, @RequestBody LocationDTO locationDTO){
        Person person = peopleService.findOne(id);
        Location location = convertToLocation(locationDTO);
        List<Location> list = person.getLocationList();
        list.add(location);
        person.setLocationList(list);
        peopleService.save(person);
        System.out.println(person.getName() + " " + person.getLocationList());
        return ResponseEntity.ok(HttpStatus.OK);
    }
    public Location convertToLocation(LocationDTO locationDTO){
        return mapper.map(locationDTO, Location.class);
    }
    private PersonDTO convertToPersonDTO(Person person){
        return mapper.map(person, PersonDTO.class);
    }

    private Person convertToPerson(PersonDTO personDTO){
        return mapper.map(personDTO, Person.class);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException personNotCreatedException){
        PersonErrorResponse response = new PersonErrorResponse(
                personNotCreatedException.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // not found - 404
    }
}

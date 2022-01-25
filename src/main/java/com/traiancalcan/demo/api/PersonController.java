package com.traiancalcan.demo.api;

import com.traiancalcan.demo.model.Person;
import com.traiancalcan.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController{

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@NonNull @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById (@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deletePersonbyId(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }
    @PutMapping( path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,@NonNull @RequestBody Person person){
        personService.updatePerson(id, person);
    }
}
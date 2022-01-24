package com.traiancalcan.demo.dao;

import com.traiancalcan.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName(), person.getRole(), person.getLink()));
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public List<Person> selectAllPersons() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updatePerson) {
        return selectPersonById(id)
                .map(person -> {
                    int indexPersonToDelete = DB.indexOf(person);
                    if(indexPersonToDelete >= 0){
                        DB.set(indexPersonToDelete, new Person(id, updatePerson.getName(), updatePerson.getRole(), updatePerson.getLink()));
                        return 1;
                    }
                    return 0;
                }).orElse( 0);
    }

    @Override
    public Optional<Person> getPersonByName(String name) {
        return Optional.empty();
    }


}

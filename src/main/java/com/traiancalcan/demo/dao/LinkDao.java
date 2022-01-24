package com.traiancalcan.demo.dao;

import com.traiancalcan.demo.model.Link;
import com.traiancalcan.demo.model.Person;

import java.util.List;
import java.util.UUID;

public interface LinkDao {

    int createLink(Person person1, Person person2);
    List<Link> getAllLinks();
    int deleteLinkById(UUID id);


}

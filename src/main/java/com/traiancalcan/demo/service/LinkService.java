package com.traiancalcan.demo.service;

import com.traiancalcan.demo.dao.LinkDao;
import com.traiancalcan.demo.model.Link;
import com.traiancalcan.demo.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {
    private final LinkDao linkDao;

    public LinkService(@Qualifier("link")LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    public int addLink(Person person1, Person person2){
        return linkDao.createLink(person1,person2);
    }
    public List<Link> getLinkByPersonLink(UUID id){
        var links = linkDao.getAllLinks();
        List<Link> retList = new ArrayList<>();

        for(var link: links){
            if((link.getPerson1().equals(id)) || (link.getPerson2().equals(id))){
                retList.add(link);
            }
        }
        return retList;
    }

    public int deletePerson(UUID id){
        return linkDao.deleteLinkById(id);
    }

}

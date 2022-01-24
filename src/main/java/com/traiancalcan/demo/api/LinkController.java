package com.traiancalcan.demo.api;

import com.traiancalcan.demo.model.Link;
import com.traiancalcan.demo.model.LinkByName;
import com.traiancalcan.demo.model.Person;
import com.traiancalcan.demo.service.LinkService;
import com.traiancalcan.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/link")
@RestController
public class LinkController {

    private final PersonService personService;
    private final LinkService linkService;

    @Autowired
    public LinkController(PersonService personService, LinkService linkService) {
        this.personService = personService;
        this.linkService = linkService;
    }

    @GetMapping(path ="{name}")
    public List<Link> getLinksByName(@PathVariable("name") String name){
        Person p = personService.getPersonByName(name).get();
        return linkService.getLinkByPersonLink(p.getId());
    }
    @PostMapping
    public void addLink(@NonNull @RequestBody LinkByName link){
        Person p1 = personService.getPersonByName(link.getName1()).get();
        Person p2 = personService.getPersonByName(link.getName2()).get();
        if((p1 != null) && (p2 != null)){
            linkService.addLink(p1,p2);
        }

    }

}

package com.traiancalcan.demo.api;

import com.traiancalcan.demo.model.Link;
import com.traiancalcan.demo.model.LinkByName;
import com.traiancalcan.demo.model.Person;
import com.traiancalcan.demo.service.LinkService;
import com.traiancalcan.demo.service.PersonService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//    @GetMapping(path ="{name}")
//    public List<Link> getLinksByName(@PathVariable("name") String name){
//        Optional<Person> p = personService.getPersonByName(name);
//        if(p.isEmpty()){
//            return null;
//        }
//        return linkService.getLinkByPersonLink(p.get().getId());
//    }

    @GetMapping(path ="{name}")
    @ResponseBody
    public JSONObject getLinksByName(@PathVariable("name") String name){
        List<String> retList = new ArrayList<>();
        List<String> retList2 = new ArrayList<>();
        Optional<Person> p = personService.getPersonByName(name);
        if(p.isEmpty()){
            return null;
        }
        var links = linkService.getLinkByPersonLink(p.get().getId());
        for(var link:links){
            var x1 = personService.getPersonById(link.getPerson1());
            var x2 = personService.getPersonById(link.getPerson2());
            if(x1.get().getName().equals(p.get().getName())){
                retList.add(x2.get().getName());
                retList2.add(x2.get().getLink());
            }else{
                retList.add(x1.get().getName());
                retList2.add(x1.get().getLink());
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",retList);
        jsonObject.put("link",retList2);
        return jsonObject;
    }


    @PostMapping
    public String addLink(@NonNull @RequestBody LinkByName link){
        Person p1 = personService.getPersonByName(link.getName1()).get();
        Person p2 = personService.getPersonByName(link.getName2()).get();
        var linkListForP1 = linkService.getLinkByPersonLink(p1.getId());
        var linkListForP2 = linkService.getLinkByPersonLink(p1.getId());
        boolean alreadyExist = false;
        for(var x:linkListForP1){
            for(var y:linkListForP2){
                if(x.getPerson1() == y.getPerson2()){
                    alreadyExist = true;
                    break;
                }
                if(alreadyExist == true){
                    break;
                }
            }
        }
        if((p1 != null) && (p2 != null) &&(alreadyExist == false)){
            linkService.addLink(p1,p2);
            return "Succes";
        }
        return "Failed to add link";
    }

}

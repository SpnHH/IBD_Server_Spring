package com.traiancalcan.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkByName {
    public final String name1;
    public final String name2;

    public LinkByName(@JsonProperty("name1")String name1,
                      @JsonProperty("name2")String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }


}

package com.traiancalcan.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    private final UUID id;
    private final String name;
    private final String role;
    private final String link;

    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
                  @JsonProperty("role") String role,
                  @JsonProperty("link") String link) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.link = link;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getLink() {
        return link;
    }
}

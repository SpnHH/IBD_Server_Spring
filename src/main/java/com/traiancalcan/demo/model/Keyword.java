package com.traiancalcan.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Keyword {
    private final UUID id;
    private final UUID personId;
    private final String description;

    public Keyword(@JsonProperty("id") UUID id,
                   @JsonProperty("personId")UUID personId,
                   @JsonProperty("description")String description) {
        this.id = id;
        this.personId = personId;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPersonId() {
        return personId;
    }

    public String getDescription() {
        return description;
    }
}

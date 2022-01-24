package com.traiancalcan.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Link {
    private final UUID id;
    private final UUID person1;
    private final UUID person2;

    public Link(@JsonProperty("id") UUID id,
                @JsonProperty("person1") UUID person1,
                @JsonProperty("person2") UUID person2) {
        this.id = id;
        this.person1 = person1;
        this.person2 = person2;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPerson1() {
        return person1;
    }

    public UUID getPerson2() {
        return person2;
    }
}

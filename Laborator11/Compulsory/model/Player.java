package com.popastefan.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.UUID;


public class Player {
    private final int id;
    private final String name;

    public Player(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

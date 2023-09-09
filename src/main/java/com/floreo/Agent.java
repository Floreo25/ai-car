package com.floreo;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Agent extends Point {
    @Getter
    private String name;

    public Agent(String name) {
        super(0, 0);
        this.name = name;
    }
}

package com.floreo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Agent extends Point {
    String name;

    public Agent(String name) {
        super(0, 0);
        this.name = name;
    }
}

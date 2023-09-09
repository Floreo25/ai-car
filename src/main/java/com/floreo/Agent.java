package com.floreo;

import lombok.Getter;

@Getter
public class Agent extends Point {
    String name;

    public Agent(String name) {
        super(0, 0);
        this.name = name;
    }

    public Point getStep(int[] input) {return this;}
}

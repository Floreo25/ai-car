package com.floreo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Point {
    @Getter
    @Setter
    private int x, y;

    public void setPoint(Point p) {
        x = p.getX();
        y = p.getY();
    }
}

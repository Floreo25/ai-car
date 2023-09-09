package com.floreo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Point {
    @Setter
    int x, y;

    public void setPoint(Point p) {
        x = p.getX();
        y = p.getY();
    }
}

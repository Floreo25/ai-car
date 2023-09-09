package com.floreo;

import lombok.Getter;

import static com.floreo.Map.Item.*;

public class Map {
    @Getter
    private final int x, y;
    private final int[][] map;
    @Getter
    private final Point start;
    @Getter
    private final Point end;

    public Map(int[][] map, Point start, Point end) {
        if (map.length < 1 || map[0].length < 1) throw new RuntimeException("Invalid map size! Should be > 0");
        this.x = map[0].length;
        this.y = map.length;

        if (start.getX() >= x || start.getY() >= y) throw new RuntimeException("Start is out of map dimensions");
        if (end.getX() >= x || end.getY() >= y) throw new RuntimeException("End is out of map dimensions");

        for (int ix = 0; ix < x; ix++) {
            for (int iy = 0; iy < y; iy++) {
                int cell = map[ix][iy];
                if (cell < 0 || cell >= Item.values().length)
                    throw new RuntimeException("Unsupported value in map: " + cell);
            }
        }

        this.map = map;
        this.start = start;
        this.end = end;
    }

    public int getAt(int x, int y) {
        if (this.x <= x || this.y <= y || x < 0 || y < 0) return WALL.ordinal();
        if (x == start.getX() && y == start.getY()) return START.ordinal();
        if (x == end.getX() && y == end.getY()) return END.ordinal();
        return map[y][x];
    }

    public enum Item {
        START,
        END,
        EMPTY,
        WALL
    }

}

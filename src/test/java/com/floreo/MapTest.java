package com.floreo;

import org.junit.Test;

import static com.floreo.Map.Item.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MapTest {
    @Test
    public void testInitialization() {
        Exception e = assertThrows(RuntimeException.class,
                () -> new Map(new int[0][0], new Point(0, 0), new Point(0, 0)));
        assertEquals("Invalid map size! Should be > 0", e.getMessage());

        e = assertThrows(RuntimeException.class,
                () -> new Map(new int[2][2], new Point(10,0), new Point(0, 0)));
        assertEquals("Start is out of map dimensions", e.getMessage());

        e = assertThrows(RuntimeException.class,
                () -> new Map(new int[2][2], new Point(0,0), new Point(0, -1)));
        assertEquals("End is out of map dimensions", e.getMessage());

        e = assertThrows(RuntimeException.class,
                () -> new Map(new int[][] {{100},{1}}, new Point(0,0), new Point(0, 1)));
        assertEquals("Unsupported value in map: 100", e.getMessage());
    }

    @Test
    public void testValues() {
        int[][] field = new int[][] {
                {0,1},
                {2,3}
        };
        Map map = new Map(field, new Point(0, 0), new Point(1,1));
        assertEquals(START.ordinal(), map.getAt(0,0));
        assertEquals(1, map.getAt(1,0));
        assertEquals(2, map.getAt(0,1));
        assertEquals(END.ordinal(), map.getAt(1,1));

        assertEquals(WALL.ordinal(), map.getAt(-1, 0));
        assertEquals(WALL.ordinal(), map.getAt(0, 10));
    }
}

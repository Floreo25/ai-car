package com.floreo;

import org.junit.Test;

import static com.floreo.Map.Item.*;
import static org.junit.Assert.*;

public class PlaygroundTest {
    @Test
    public void testGetAt() {
        int[][] field = new int[][]{
                {0, 1},
                {2, 3}
        };
        Map map = new Map(field, new Point(0, 0), new Point(1, 1));
        Playground p = new Playground(map);
        p.addAgent(new Agent("a1"));
        p.addAgent(new Agent("a2"));

        assertEquals("0:a", p.getAt(0,0));
        assertEquals("1", p.getAt(1,0));
        assertEquals("2", p.getAt(0,1));
        assertEquals(String.valueOf(Map.Item.END.ordinal()), p.getAt(1,1));
    }

    @Test
    public void testMove() {
        int[][] field = new int[][]{
                {0, 2, 2},
                {3, 3, 2},
                {2, 2, 1}
        };
        Map map = new Map(field, new Point(0, 0), new Point(2, 2));
        Playground p = new Playground(map);
        Agent a = new Agent("a1");
        p.addAgent(a);

        assertTrue(p.moveAgent(a, new Point(1,0)));
        assertFalse(p.moveAgent(a, new Point(1,1)));
        assertFalse(p.moveAgent(a, new Point(2,1)));
        assertTrue(p.moveAgent(a, new Point(2,0)));
        assertTrue(p.moveAgent(a, new Point(2,1)));
        assertTrue(p.moveAgent(a, new Point(2,2)));
    }

    @Test
    public void testSurround() {
        int[][] field = new int[][]{
                {0, 3},
                {2, 1}
        };
        Map map = new Map(field, new Point(0, 0), new Point(1, 1));
        Playground p = new Playground(map);
        Agent a = new Agent("a1");
        p.addAgent(a);

        int w = WALL.ordinal();
        assertArrayEquals(new int[]{w, w, w, 3, 1, 2, w, w}, p.getAgentSurround(a));
    }
}

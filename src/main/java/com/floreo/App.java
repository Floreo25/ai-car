package com.floreo;

/**
 * Hello world!
 *
 */
public class App 
{
    private Playground p;

    public App() {
        int[][] field = new int[][]{
                {0, 2, 2},
                {3, 3, 2},
                {2, 2, 1}
        };
        Map map = new Map(field, new Point(0, 0), new Point(2, 2));
        Playground p = new Playground(map);
        Agent a = new Agent("a1");
        p.addAgent(a);

        p.draw();
        p.moveAgent(a, new Point(1,0));
        p.draw();
        p.moveAgent(a, new Point(2,0));
        p.draw();
        p.moveAgent(a, new Point(2,1));
        p.draw();
        p.moveAgent(a, new Point(2,2));
        p.draw();
    }

    public static void main(String[] args )
    {
        new App();
    }
}

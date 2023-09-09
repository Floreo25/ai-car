package com.floreo;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public App() {
        int[][] field = new int[][]{
                {2, 3, 2, 3, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 3, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 3, 0, 2, 2, 3, 2, 2, 2},
                {2, 2, 2, 2, 2, 3, 2, 2, 2, 2},
                {2, 2, 2, 3, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 3, 2, 2, 3, 2, 2, 3, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        };
        Map map = new Map(field, new Point(3, 3), new Point(9, 9));
        Playground playground = new Playground(map);

        List<Agent> agents = new ArrayList<>();
        for (int i = 0; i<1000; i++) {
            agents.add(new AiAgent("a"+i, 10));
            playground.addAgent(agents.get(i));
        }

        int cycle = 0;
        while (cycle < 1000) {
            agents
                    .forEach(agent -> {
                        int[] surround = playground.getAgentSurround(agent);
                        Point step = agent.getStep(surround);
                        playground.moveAgent(agent, step);
                    });
            cycle++;
        }
        playground.draw();
    }

    public static void main(String[] args )
    {
        new App();
    }
}

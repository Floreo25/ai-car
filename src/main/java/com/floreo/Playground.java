package com.floreo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Playground {
    private Map map;
    private final Collection<Agent> agents;

    public Playground(Map map) {
        this.map = map;
        this.agents = new ArrayList<>();
    }

    public void addAgent(Agent agent) {
        agent.setPoint(map.getStart());
        agents.add(agent);
    }

    public boolean moveAgent(Agent agent, Point p) {
        if ((agent.getX() == p.getX() && Math.abs(agent.getY() - p.getY()) < 2)
                || (agent.getY() == p.getY() && Math.abs(agent.getX() - p.getX()) < 2)) {
            if (map.getAt(p.getX(), p.getY()) != Map.Item.WALL.ordinal()) {
                agent.setPoint(p);
                return true;
            }
        }
        return false;
    }

    public String getAt(int x, int y) {
        List<Agent> result = agents.stream()
                .filter(agent -> agent.getX() == x && agent.getY() == y)
                .collect(Collectors.toList());
        int cell = map.getAt(x, y);
        StringBuilder builder = new StringBuilder(String.valueOf(cell));
        result.forEach(a -> builder.append(":").append(a.getName()));
        return builder.toString();
    }

    public void draw() {
        for (int y = 0; y < map.getY(); y++) {
            for (int x = 0; x < map.getX(); x++) {
                System.out.printf("%s\t\t", getAt(x, y));
            }
            System.out.println("");
        }
        System.out.println("");
    }
}

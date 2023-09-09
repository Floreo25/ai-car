package com.floreo;

import java.util.*;
import java.util.stream.Collectors;

import static com.floreo.App.GENERATION_SIZE;

public class Playground {
    private static final int TOP_THRESHOLD = 30;
    private final Map map;
    private final List<Agent> agents;

    public Playground(Map map) {
        this.map = map;
        this.agents = new ArrayList<>();
    }

    public void addAgent(Agent agent) {
        agent.setPoint(map.getStart());
        agents.add(agent);
    }

    boolean moveAgent(Agent agent, Point p) {
        if ((agent.getX() == p.getX() && Math.abs(agent.getY() - p.getY()) < 2)
                || (agent.getY() == p.getY() && Math.abs(agent.getX() - p.getX()) < 2)) {
            if (map.getAt(p.getX(), p.getY()) != Map.Item.WALL.ordinal()) {
                agent.setPoint(p);
                return true;
            }
        }
        return false;
    }

    int[] getAgentSurround(Agent agent) {
        int x = agent.getX();
        int y = agent.getY();
        return new int[]{
                map.getAt(x - 1, y - 1),
                map.getAt(x, y - 1),
                map.getAt(x + 1, y - 1),
                map.getAt(x + 1, y),
                map.getAt(x + 1, y + 1),
                map.getAt(x, y + 1),
                map.getAt(x - 1, y + 1),
                map.getAt(x - 1, y),
        };
    }

    int getDistanceToFinish(Agent agent) {
        int dx = Math.abs(agent.x - map.getEnd().getX());
        int dy = Math.abs(agent.y - map.getEnd().getY());
        return dx + dy;
    }

    public void game(int iterations) {
        for (int i = 0; i<iterations; i++) {
            agents.stream()
                    .filter(agent -> agent instanceof AiAgent)
                    .map(agent -> (AiAgent) agent)
                    .forEach(agent -> {
                        int[] surround = getAgentSurround(agent);
                        Point step = agent.getStep(surround);
                        moveAgent(agent, step);
                    });
        }
    }

    public void buildNewGeneration() {
        agents.sort(Comparator.comparingInt(this::getDistanceToFinish));
        List<Agent> newGeneration = new ArrayList<>();
        while (newGeneration.size() < GENERATION_SIZE) {
            AiAgent parent1 = getRandomTop();
            AiAgent parent2 = getRandomTop();
            AiAgent child = parent1.crossover(parent2);
            child.setName("a" + newGeneration.size());
            newGeneration.add(child);
        }
        agents.clear();
        newGeneration.forEach(this::addAgent);
    }

    AiAgent getRandomTop() {
        while (true) {
            Agent agent = agents.get(new Random().nextInt(TOP_THRESHOLD));
            if (agent instanceof AiAgent)
                return (AiAgent) agent;
        }
    }

    String getAt(int x, int y) {
        List<Agent> result = agents.stream()
                .filter(agent -> agent.getX() == x && agent.getY() == y)
                .collect(Collectors.toList());
        int cell = map.getAt(x, y);
        StringBuilder builder = new StringBuilder(String.valueOf(cell));
        if (!result.isEmpty()) {
            // result.forEach(a -> builder.append(":").append(a.getName()));
            builder.append(":a(").append(result.size()).append(")");
        }
        return builder.toString();
    }

    public void draw() {
        for (int y = 0; y < map.getY(); y++) {
            for (int x = 0; x < map.getX(); x++) {
                System.out.printf("%s\t\t", getAt(x, y));
            }
            System.out.println();
        }
        System.out.println();
    }
}

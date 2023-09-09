package com.floreo;

/**
 * Hello world!
 */
public class App {
    public static final int GENERATION_SIZE = 1000;
    private Playground playground;

    public App() {
        buildPlayground();
        buildAgents();
        for (int i = 0; i < 50; i++) {
            System.out.printf("Generation: %d\n", i);
            if (i > 0) {
                playground.buildNewGeneration();
            }
            playground.game(1000);
            playground.draw();
        }
    }


    private void buildAgents() {
        for (int i = 0; i < GENERATION_SIZE; i++) {
            playground.addAgent(new AiAgent("a" + i, 10));
        }
    }

    private void buildPlayground() {
        int[][] field = new int[][]{
                {2, 3, 2, 3, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 3, 2, 2, 3, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 3, 0, 2, 2, 3, 2, 2, 3},
                {2, 2, 2, 2, 2, 3, 2, 2, 2, 2},
                {2, 2, 2, 3, 2, 2, 2, 3, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 3, 2, 2, 3, 2, 2, 3, 2, 3},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 3, 2, 2, 2, 3, 2, 2, 1},
        };
        Map map = new Map(field, new Point(3, 3), new Point(9, 9));
        playground = new Playground(map);
    }

    public static void main(String[] args) {
        new App();
    }
}

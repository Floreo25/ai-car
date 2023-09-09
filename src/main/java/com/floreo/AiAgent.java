package com.floreo;

import com.floreo.ai.mlp.NeuralNetwork;

import java.util.Arrays;
import java.util.List;

public class AiAgent extends Agent {

    private final NeuralNetwork nn;
    public AiAgent(String name, int hiddenLayers) {
        super(name);
        nn = new NeuralNetwork(8, hiddenLayers, 4);
    }

    @Override
    public Point getStep(int[] input) {
        Point p = new Point(x, y);
        List<Double> output = nn.predict(Arrays.stream(input).asDoubleStream().toArray());
        p.x += (int) Math.round(output.get(0));
        p.x -= (int) Math.round(output.get(2));
        p.y += (int) Math.round(output.get(1));
        p.y -= (int) Math.round(output.get(3));
        System.out.println(name + ": " + output + "->" +p);
        return p;
    }
}

package com.floreo.ai.mlp;

import java.util.List;

public class NeuralNetwork {

    Matrix weights_ih, weights_ho, bias_h, bias_o;
    double l_rate = 0.01;

    public NeuralNetwork(int i, int h, int o) {
        weights_ih = new Matrix(h, i);
        weights_ho = new Matrix(o, h);

        bias_h = new Matrix(h, 1);
        bias_o = new Matrix(o, 1);
    }

    public List<Double> predict(double[] X) {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        return output.toArray();
    }


    public void train(double[][] X, double[][] Y, int epochs) {
        for (int i = 0; i < epochs; i++) {
            int sampleN = (int) (Math.random() * X.length);
            this.train(X[sampleN], Y[sampleN]);
        }
    }

    private void train(double[] X, double[] Y) {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        Matrix target = Matrix.fromArray(Y);

        Matrix error = Matrix.subtract(target, output);
        Matrix gradient = output.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(l_rate);

        Matrix hidden_T = Matrix.transpose(hidden);
        Matrix who_delta = Matrix.multiply(gradient, hidden_T);

        weights_ho.add(who_delta);
        bias_o.add(gradient);

        Matrix who_T = Matrix.transpose(weights_ho);
        Matrix hidden_errors = Matrix.multiply(who_T, error);

        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(l_rate);

        Matrix i_T = Matrix.transpose(input);
        Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

        weights_ih.add(wih_delta);
        bias_h.add(h_gradient);
    }

    public static NeuralNetwork crossover(NeuralNetwork parent1, NeuralNetwork parent2) {
        NeuralNetwork child = new NeuralNetwork(parent1.weights_ih.cols, parent1.weights_ho.cols, parent1.weights_ho.rows);

        double mutation = 0;
        if(Math.random() < 0.01) mutation = 0.1;

        // Crossover weights_ih
        for (int i = 0; i < parent1.weights_ih.rows; i++) {
            for (int j = 0; j < parent1.weights_ih.cols; j++) {
                // Randomly select the weight from either parent1 or parent2
                if (Math.random() < 0.5) {
                    child.weights_ih.data[i][j] = parent1.weights_ih.data[i][j];
                } else {
                    child.weights_ih.data[i][j] = parent2.weights_ih.data[i][j];
                }
                if (Math.random() < 0.5) {
                    child.weights_ih.data[i][j] += mutation;
                }
            }
        }

        // Crossover weights_ho
        for (int i = 0; i < parent1.weights_ho.rows; i++) {
            for (int j = 0; j < parent1.weights_ho.cols; j++) {
                // Randomly select the weight from either parent1 or parent2
                if (Math.random() < 0.5) {
                    child.weights_ho.data[i][j] = parent1.weights_ho.data[i][j];
                } else {
                    child.weights_ho.data[i][j] = parent2.weights_ho.data[i][j];
                }
                if (Math.random() < 0.5) {
                    child.weights_ho.data[i][j] += mutation;
                }
            }
        }

        // Crossover bias_h
        for (int i = 0; i < parent1.bias_h.rows; i++) {
            for (int j = 0; j < parent1.bias_h.cols; j++) {
                // Randomly select the bias from either parent1 or parent2
                if (Math.random() < 0.5) {
                    child.bias_h.data[i][j] = parent1.bias_h.data[i][j];
                } else {
                    child.bias_h.data[i][j] = parent2.bias_h.data[i][j];
                }
                if (Math.random() < 0.5) {
                    child.bias_h.data[i][j] += mutation;
                }
            }
        }

        // Crossover bias_o
        for (int i = 0; i < parent1.bias_o.rows; i++) {
            for (int j = 0; j < parent1.bias_o.cols; j++) {
                // Randomly select the bias from either parent1 or parent2
                if (Math.random() < 0.5) {
                    child.bias_o.data[i][j] = parent1.bias_o.data[i][j];
                } else {
                    child.bias_o.data[i][j] = parent2.bias_o.data[i][j];
                }
                if (Math.random() < 0.5) {
                    child.bias_o.data[i][j] += mutation;
                }
            }
        }

        return child;
    }
}
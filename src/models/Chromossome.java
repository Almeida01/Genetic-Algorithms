package src.models;

import java.util.Random;

public class Chromossome {
    private final String gene;
    private double fitness;

    public String getGene() {
        return gene;
    }

    public double getFitness() {
        return fitness;
    }

    public Chromossome(int l, Random generator) {
        StringBuilder chromossome = new StringBuilder();
        double u; // Random number between [0, 1).
        for (int i = 0; i < l; i++) {
            u = generator.nextDouble();
            chromossome.append(u < 0.5 ? 0 : 1);
        }
        this.gene = chromossome.toString();
    }

    public Chromossome(String gene) {
        this.gene = gene;
    }

}

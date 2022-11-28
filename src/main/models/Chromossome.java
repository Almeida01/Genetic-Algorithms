package main.models;

import java.util.Random;

public class Chromossome {
    private final String gene;
    private final int length;
    private double fitness;

    public String getGene() {
        return gene;
    }

    public int getLength() {
        return length;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Chromossome(String gene) {
        this.gene = gene;
        this.length = gene.length();
    }

    public Chromossome(String gene, double fitness) {
        this.gene = gene;
        this.length = gene.length();
        this.fitness = fitness;
    }

    public Chromossome(int l, Random generator) {
        StringBuilder chromossome = new StringBuilder();
        double u; // Random number between [0, 1).
        for (int i = 0; i < l; i++) {
            u = generator.nextDouble();
            chromossome.append(u < 0.5 ? 0 : 1);
        }
        this.gene = chromossome.toString();
        this.length = l;
    }

    public Chromossome(int l, Random generator, double fitness) {
        this(l, generator);
        this.fitness = fitness;
    }


    @Override
    public String toString() {
        return gene;
    }

    public int compareTo(Chromossome c) {

        int value = Integer.parseInt(getGene(), 2);
        int oValue = Integer.parseInt(c.getGene(), 2);

        return Integer.compare(oValue, value);
    }
}

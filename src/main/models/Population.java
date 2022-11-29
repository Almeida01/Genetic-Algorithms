package main.models;

import java.util.LinkedList;
import java.util.Random;

public class Population {
    private final LinkedList<Chromossome> population;
    private int size;
    private double totalFitness;
    public int getSize() {
        return size;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public LinkedList<Chromossome> getPopulation() {
        return population;
    }

    public Population() {
        this.population = new LinkedList<>();
    }

    public Population(int n, int l, Random generator) {
        this();
        this.size = n;
        for (int i = 0; i < n; i++) population.add(new Chromossome(l, generator));
    }

    public Population(int n) {
        this();
        this.size = n;
    }

    public void addChromossome(Chromossome chromossome) {
        population.add(chromossome);
        size++;
    }

    public void calculateTotalFitness() {
        for (Chromossome chromossome : population) {
            totalFitness += chromossome.getFitness();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size - 1; i++) str.append(population.get(i).getGene()).append('\n');
        str.append(population.get(size - 1).getGene());
        return str.toString();
    }

    public static int mapToPopSize(int min, int max, double value) {
        return (int) (min + Math.round(value * (max - min)));
    }
}

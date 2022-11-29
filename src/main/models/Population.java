package main.models;

import java.util.LinkedList;
import java.util.Random;

public class Population {
    private final LinkedList<Chromossome> population;
    private int size;
    private double fitnessSum;
    public int getSize() {
        return size;
    }

    public double getFitnessSum() {
        return fitnessSum;
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size - 1; i++) str.append(population.get(i).getGene()).append('\n');
        str.append(population.get(size - 1).getGene());
        return str.toString();
    }

    public static int mapToPopSize(Random generator, int a, int b, double u) {
        int index = (int) (a + Math.round(u * (b - a)));
        return index;
    }
}

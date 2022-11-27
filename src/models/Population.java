package src.models;

import java.util.LinkedList;
import java.util.Random;

public class Population {
    private final LinkedList<Chromossome> population;
    private final int size;
    private int length;

    public LinkedList<Chromossome> getPopulation() {
        return population;
    }

    public Population(int n, int l, Random generator) {
        this.size = n;
        this.length = l;
        this.population = new LinkedList<>();
        for (int i = 0; i < n; i++) population.add(new Chromossome(l, generator));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) str.append(population.get(i).getGene()).append('\n');
        return str.toString();
    }

}

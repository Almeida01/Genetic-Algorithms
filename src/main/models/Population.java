package main.models;

import java.util.LinkedList;
import java.util.Random;

public class Population {
    private final LinkedList<Chromossome> population;
    private int size;

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

    public int oneMaxFitness(Chromossome chromossome) {
        assert(chromossome.getLength() <= 1000);

        String gene = chromossome.getGene();
        int max = 0;
        for (int i = 0; i < chromossome.getLength(); i++) {
            if (gene.charAt(i) == '1') max++;
        }
        return max;
    }

    public int squareFitness(Chromossome chromossome) {
        assert(chromossome.getLength() <= 10);

        int value = Integer.parseInt(chromossome.getGene(), 2);
        return value * value;
    }
}

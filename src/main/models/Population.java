package main.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

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
        assert (chromossome.getLength() <= 1000);

        String gene = chromossome.getGene();
        int max = 0;
        for (int i = 0; i < chromossome.getLength(); i++) {
            if (gene.charAt(i) == '1') max++;
        }
        return max;
    }

    public int squareFitness(Chromossome chromossome) {
        assert (chromossome.getLength() <= 10);

        int value = Integer.parseInt(chromossome.getGene(), 2);
        return value * value;
    }

    public LinkedList<Chromossome> binaryTournament(Random generator) {
        LinkedList<Chromossome> winners = new LinkedList<>();
        int compSize = 2 * size;
        int[] competitors = generateCompetitors(generator, compSize);

        for (int i = 0; i < compSize - 1; i += 2) {
            int firstComp = competitors[i];
            int secComp = competitors[i + 1];

            int roundWinner = battle(firstComp, secComp);
            winners.add(population.get(roundWinner));
        }

        return winners;
    }

    private int[] generateCompetitors(Random generator, int compSize) {
        int a = 0, b = size - 1;
        double u;
        int[] competitors = new int[compSize];
        for (int i = 0; i < compSize; i++) {
            u = generator.nextDouble();
            competitors[i] = (int) (a + Math.round(u * (b - a)));
        }
        return competitors;
    }

    private int battle(int firstComp, int secComp) {
        double fitFirst = population.get(firstComp).getFitness();
        double fitSec = population.get(secComp).getFitness();
        return fitFirst >= fitSec ? firstComp : secComp;
    }

    public LinkedList<Chromossome> roulette(Random generator, int n) {
        LinkedList<Chromossome> winners = new LinkedList<>();
        int a = 0, b = size - 1;
        double u;
        for (int i = 0; i < n; i++) {
            u = generator.nextDouble();
            int index = (int) (a + Math.round(u * (b - a)));
            winners.add(population.get(index));
        }
        winners.sort(Chromossome::compareTo);
        return winners;
    }
}

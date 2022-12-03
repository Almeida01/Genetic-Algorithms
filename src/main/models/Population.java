package main.models;

import java.util.LinkedList;
import java.util.Random;

public class Population {
    private final LinkedList<Chromosome> population;
    private int size;
    private double totalFitness;

    public int getSize() {
        return size;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public LinkedList<Chromosome> getPopulation() {
        return population;
    }

    public Population() {
        this.population = new LinkedList<>();
    }

    public Population(int n, int l, Random generator) {
        this();
        this.size = n;
        for (int i = 0; i < n; i++) population.add(new Chromosome(l, generator));
        calculateTotalFitness();
    }

    public Population(int n) {
        this();
        this.size = n;
    }

    public void addChromossome(Chromosome chromosome) {
        population.add(chromosome);
        totalFitness += chromosome.getFitness();
        size++;
    }

    private void calculateTotalFitness() {
        for (Chromosome chromosome : population) {
            totalFitness += chromosome.getFitness();
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

    public LinkedList<Chromosome> binaryTournament(Random generator) {
        LinkedList<Chromosome> winners = new LinkedList<>();
        int compSize = 2 * this.size;
        int[] competitors = generateCompetitors(generator, compSize);

        for (int i = 0; i < compSize - 1; i += 2) {
            int firstComp = competitors[i];
            int secComp = competitors[i + 1];

            Chromosome roundWinner = battle(population.get(firstComp), population.get(secComp));
            winners.add(roundWinner);
        }

        return winners;
    }

    private int[] generateCompetitors(Random generator, int compSize) {
        int[] competitors = new int[compSize];
        for (int i = 0; i < compSize; i++) {
            double u = generator.nextDouble();
            competitors[i] = mapToPopSize(0, this.getSize() - 1, u);
        }
        return competitors;
    }

    private Chromosome battle(Chromosome firstComp, Chromosome secComp) {
        double fitFirst = firstComp.getFitness();
        double fitSec = secComp.getFitness();
        return fitFirst >= fitSec ? firstComp : secComp;
    }

    public LinkedList<Chromosome> roulette(Random generator) {
        LinkedList<Chromosome> winners = new LinkedList<>();

        double totalFitness = getTotalFitness();
        assert (totalFitness > 0);

        for (int i = 0; i < this.size; i++) {
            double prob = 0;
            for (int j = 0; j < this.size; j++) {
                prob += population.get(i).getFitness() / totalFitness;
                double u = generator.nextDouble();
                if (u > prob) continue;
                int index = mapToPopSize(0, this.size - 1, u);
                winners.add(population.get(index));
                break;
            }
        }
        //winners.sort(Chromossome::compareTo);
        //reverseLinkedList(winners);
        return winners;
    }

    private void reverseLinkedList(LinkedList<Chromosome> list) {
        for (int i = 0; i < (list.size() / 2); i++) {
            int end = (list.size() - 1) - i;
            Chromosome temp = list.get(end);
            list.set(end, list.get(i));
            list.set(i, temp);
        }
    }

    public LinkedList<Chromosome> selectionWithoutReplacement(Random generator, int s) {
        int[] permutation;
        LinkedList<Chromosome> tournament = new LinkedList<>();
        int battles = size / s;
        for (int i = 0; i < s; i++) {
            LinkedList<Chromosome> fighters = new LinkedList<>();

            permutation = Chromosome.randomPermutation(generator, size);
            for (int i1 : permutation)
                fighters.add(population.get(i1));

            int start = 0;
            for (int j = 0; j < battles; j++) {
                int winner = biggestFitness(fighters, start, start + s);
                tournament.add(fighters.get(winner));
                start += s;
            }
        }
        return tournament;
    }

    private int biggestFitness(LinkedList<Chromosome> fighters, int start, int end) {
        int index = start;
        for (int i = start + 1; i < end; i++)
            if (fighters.get(i).getFitness() > fighters.get(index).getFitness())
                index = i;
        return index;
    }
}

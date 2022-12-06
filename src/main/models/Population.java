package main.models;

import main.fitness.OneMax;

import java.util.HashMap;
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

    public Population(int n) {
        this();
        this.size = n;
    }

    public Population(int n, int l, Random generator) {
        this(n);
        for (int i = 0; i < n; i++) {
            addChromossome(new Chromosome(l, generator));
        }
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

    public int mapToPopSize(int min, int max, double value) {
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
            double sum = 0;
            for (int j = 0; j < this.size; j++) {
                double u = generator.nextDouble();
                Chromosome c = population.get(j);
                sum += (c.getFitness() / totalFitness);
                if (u < sum) {
                    winners.add(c);
                    break;
                }
            }
        }
        winners.sort(Chromosome::compareToGene);
        return winners;
    }

    public LinkedList<Chromosome> selectionWithoutReplacement(Random generator, int s) {
        int[] permutation;
        LinkedList<Chromosome> tournament = new LinkedList<>();
        LinkedList<Chromosome> fighters = new LinkedList<>();
        int battles = size / s;
        for (int i = 0; i < s; i++) {
            fighters = new LinkedList<>();
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

    /**
     * @param generator Random object.
     * @param s         Tournament size
     * @param pc        Crossover probability
     * @param pm        Mutation probability
     */
    public LinkedList<Chromosome> oneGerationOneMax(Random generator, int s, double pc, double pm) {
        LinkedList<Chromosome> generation = this.selectionWithoutReplacement(generator, s);
//        System.out.println("Select: " + generation);
        for (int i = 0; i < generation.size() - 1; i += 2) {
            Chromosome parent1 = generation.get(i);
            Chromosome parent2 = generation.get(i + 1);
            double prob = generator.nextDouble();
            if (prob > pc) continue;
            Chromosome[] child = parent1.onePointCrossover(generator, parent2);
            child[0].setFitness(new OneMax(child[0]));
            child[1].setFitness(new OneMax(child[1]));
            generation.set(i, child[0]);
            generation.set(i + 1, child[1]);
        }

//        System.out.println("Crossover: " + generation);

        for (int i = 0; i < generation.size(); i++) {
            Chromosome x = generation.get(i).bitFlipMutation(generator, pm);
            x.setFitness(new OneMax(x));
            generation.set(i, x);
        }

//        System.out.println("Bitflip: " + generation);

        return generation;
    }
}

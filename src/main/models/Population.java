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

    public int mapToPopSize(Random generator, int a, int b) {
        double u = generator.nextDouble();
        int index = (int) (a + Math.round(u * (b - a)));
        return index;
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
        int[] competitors = new int[compSize];
        for (int i = 0; i < compSize; i++) {
            competitors[i] = mapToPopSize(generator, 0, size - 1);
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
        for (int i = 0; i < n; i++) {
            int index = mapToPopSize(generator, 0, size - 1);
            winners.add(population.get(index));
        }
        winners.sort(Chromossome::compareTo);
        reverseLinkedList(winners);
        return winners;
    }

    private void reverseLinkedList(LinkedList<Chromossome> list) {
        for (int i = 0; i < (list.size() / 2); i++) {
            int end = (list.size() - 1) - i;
            Chromossome temp = list.get(end);
            list.set(end, list.get(i));
            list.set(i, temp);
        }
    }

    public Chromossome[] onePointCrossover(Random generator, Chromossome parent1, Chromossome parent2) {
         int crossPoint = mapToPopSize(generator, 0, parent1.getLength() - 1);

        String gene1 = parent1.getGene();
        String gene2 = parent2.getGene();

        String child1 = gene1.substring(0, crossPoint) + gene2.substring(crossPoint);
        String child2 = gene2.substring(0, crossPoint) + gene1.substring(crossPoint);

        return new Chromossome[]{new Chromossome(child1), new Chromossome(child2)};
    }

    public Chromossome[] uniformCrossover(Random generator, Chromossome parent1, Chromossome parent2) {
        int crossPoint = mapToPopSize(generator, 0, parent1.getLength() - 1);

        String gene1 = parent1.getGene();
        String gene2 = parent2.getGene();

        String child1 = gene1.substring(0, crossPoint) + gene2.substring(crossPoint);
        String child2 = gene2.substring(0, crossPoint) + gene1.substring(crossPoint);
        return null;
    }
}

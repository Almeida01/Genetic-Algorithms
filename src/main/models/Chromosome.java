package main.models;

import main.IProblem;

import java.util.Random;

import static main.models.Population.mapToPopSize;

public class Chromosome implements IProblem {
    private final String gene;
    private final int length;
    private double fitness;

    public Chromosome() {
        gene = "";
        length = 0;
    }

    public String getGene() {
        return gene;
    }

    public int getLength() {
        return length;
    }

    public double getFitness() {
        return fitness;
    }

    public double getFitness(IProblem l) {
        return l.getFitness();
    }

    private void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Chromosome(String gene) {
        this.gene = gene;
        this.length = gene.length();
    }

    public Chromosome(String gene, double fitness) {
        this.gene = gene;
        this.length = gene.length();
        this.fitness = fitness;
    }

    public Chromosome(int l, Random generator) {
        StringBuilder chromossome = new StringBuilder();
        double u; // Random number between [0, 1).
        for (int i = 0; i < l; i++) {
            u = generator.nextDouble();
            chromossome.append(u < 0.5 ? 0 : 1);
        }
        this.gene = chromossome.toString();
        this.length = l;
    }

    public Chromosome(int l, Random generator, double fitness) {
        this(l, generator);
        this.fitness = fitness;
    }


    @Override
    public String toString() {
        return gene;
    }

    public int compareTo(Chromosome c) {

        int value = Integer.parseInt(getGene(), 2);
        int oValue = Integer.parseInt(c.getGene(), 2);

        return Integer.compare(oValue, value);
    }

    public Chromosome bitFlipMutation(Random generator, double prob) {
        char[] child = this.getGene().toCharArray();
        for (int i = 0; i < this.getLength(); i++) {
            double u = generator.nextDouble();
            if (u >= prob) continue;
            child[i] = child[i] == '1' ? '0' : '1';
        }

        String mutated = String.valueOf(child);
        return new Chromosome(mutated);
    }

    private int getCrossPoint(Random generator, int a, int b, double u) {
        return mapToPopSize(a, b, u);
    }

    public Chromosome[] onePointCrossover(Random generator, Chromosome parent2) {
        double u = generator.nextDouble();
        int crossPoint = getCrossPoint(generator, 0, this.getLength() - 1, u);

        String gene1 = this.getGene();
        String gene2 = parent2.getGene();

        String child1 = gene1.substring(0, crossPoint) + gene2.substring(crossPoint);
        String child2 = gene2.substring(0, crossPoint) + gene1.substring(crossPoint);

        return new Chromosome[]{new Chromosome(child1), new Chromosome(child2)};
    }

    public Chromosome[] uniformCrossover(Random generator, Chromosome parent2) {
        double prob = 0.5;

        char[] tempChild1 = this.getGene().toCharArray();
        char[] tempChild2 = parent2.getGene().toCharArray();

        for (int i = 0; i < this.getLength(); i++) {
            double u = generator.nextDouble();
            if (u >= prob) continue;
            char temp = tempChild2[i];
            tempChild2[i] = tempChild1[i];
            tempChild1[i] = temp;
        }

        String child1 = String.valueOf(tempChild1);
        String child2 = String.valueOf(tempChild2);

        return new Chromosome[]{new Chromosome(child1), new Chromosome(child2)};
    }

    /**
     *
     * @param generator Random object.
     * @param N Range of permutation. From 0 to N-1.
     * @return Integer array representing index of .
     */
    public static int[] randomPermutation(Random generator, int N) {
        int[] v = new int[N];

        for (int i = 0; i < N; i++)
            v[i] = i;

        for (int i = 0; i < N - 1; i++) {
            double u = generator.nextDouble();
            int r = mapToPopSize(i, N - 1, u);
            int temp = v[r];
            v[r] = v[i];
            v[i] = temp;
        }
        return v;
    }
}

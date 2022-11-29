package main.recombinations;

import main.models.Chromossome;

import java.util.Random;

import static main.models.Population.mapToPopSize;

public class Crossover {
    private static int getCrossPoint(Random generator, int a, int b, double u) {
        return mapToPopSize(generator, a, b, u);
    }

    public static Chromossome[] onePoint(Random generator, Chromossome parent1, Chromossome parent2) {
        double u = generator.nextDouble();
        int crossPoint = getCrossPoint(generator, 0, parent1.getLength() - 1, u);

        String gene1 = parent1.getGene();
        String gene2 = parent2.getGene();

        String child1 = gene1.substring(0, crossPoint) + gene2.substring(crossPoint);
        String child2 = gene2.substring(0, crossPoint) + gene1.substring(crossPoint);

        return new Chromossome[]{new Chromossome(child1), new Chromossome(child2)};
    }

    public static Chromossome[] uniform(Random generator, Chromossome parent1, Chromossome parent2) {
        double prob = 0.5;

        char[] tempChild1 = parent1.getGene().toCharArray();
        char[] tempChild2 = parent2.getGene().toCharArray();

        for (int i = 0; i < parent1.getLength(); i++) {
            double u = generator.nextDouble();
            if (u >= prob) continue;
            char temp = tempChild2[i];
            tempChild2[i] = tempChild1[i];
            tempChild1[i] = temp;
        }

        String child1 = String.valueOf(tempChild1);
        String child2 = String.valueOf(tempChild2);

        return new Chromossome[]{new Chromossome(child1), new Chromossome(child2)};
    }
}

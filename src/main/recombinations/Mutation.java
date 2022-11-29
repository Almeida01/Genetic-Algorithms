package main.recombinations;

import main.models.Chromossome;

import java.util.Random;

public class Mutation {
    public static Chromossome bitFlip(Random generator, Chromossome parent1, double prob) {
        char[] child = parent1.getGene().toCharArray();
        for (int i = 0; i < parent1.getLength(); i++) {
            double u = generator.nextDouble();
            if (u >= prob) continue;
            child[i] = child[i] == '1' ? '0' : '1';
        }

        String mutated = String.valueOf(child);
        return new Chromossome(mutated);
    }
}
